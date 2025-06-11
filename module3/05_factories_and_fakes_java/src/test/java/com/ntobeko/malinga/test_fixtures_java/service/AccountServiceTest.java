package com.ntobeko.malinga.test_fixtures_java.service;

import com.ntobeko.malinga.test_fixtures_java.model.Account;
import com.ntobeko.malinga.test_fixtures_java.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//https://github.com/DiUS/java-faker
@SpringBootTest
class AccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    private static AccountFactory accountFactory;

    @BeforeAll
    static void beforeAll() {
        accountFactory = new AccountFactory();
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @DisplayName("Test creating an account")
    @Test
    void testCreateAnAccount() {
        Account account = accountFactory.build();
        accountService.create(account);
        assertEquals(accountRepository.findAll().size(), 1);
    }

    @DisplayName("Test creating all accounts")
    @Test
    void testCreateAllAccounts() {
        for (int i=0; i < 4; i++) {
            accountService.create(accountFactory.build());
        }
        assertEquals(accountRepository.findAll().size(), 4);
    }
}
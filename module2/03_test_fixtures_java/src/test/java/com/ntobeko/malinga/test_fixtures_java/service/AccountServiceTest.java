package com.ntobeko.malinga.test_fixtures_java.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntobeko.malinga.test_fixtures_java.model.Account;
import com.ntobeko.malinga.test_fixtures_java.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    private static List<Account> accountList = new ArrayList<>();

    @BeforeAll
    static void beforeAll() throws IOException {
        ClassPathResource resource = new ClassPathResource("fixtures/account_data.json");

        ObjectMapper objectMapper = new ObjectMapper();

        accountList = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Account>>() {
        });
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @DisplayName("Test creating an account")
    @Test
    void testCreateAnAccount() {
        Account account = accountList.get(0);
        accountService.create(account);
        assertEquals(accountRepository.findAll().size(), 1);
    }

    @DisplayName("Test creating all accounts")
    @Test
    void testCreateAllAccounts() {
        for (Account account: accountList) {
            account.setId(0); // Not good, will fix later (somehow objects in accountList are being updated)
            accountService.create(account);
        }
        assertEquals(accountRepository.findAll().size(), accountList.size());
    }
}
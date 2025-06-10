package com.ntobeko.malinga.test_fixtures_java.service;

import com.ntobeko.malinga.test_fixtures_java.model.Account;
import com.ntobeko.malinga.test_fixtures_java.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void create(Account account) {
        accountRepository.save(account);
    }

    public void update(Account account) {
        accountRepository.save(account);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}

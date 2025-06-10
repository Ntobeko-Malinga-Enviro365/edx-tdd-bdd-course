package com.ntobeko.malinga.test_fixtures_java.service;

import com.github.javafaker.Faker;
import com.ntobeko.malinga.test_fixtures_java.model.Account;

public class AccountFactory {
    public Account build() {
        Faker faker = new Faker();

        Account account = new Account();
        account.setName(faker.name().fullName());
        account.setEmail(faker.name().firstName() + "." + faker.name().lastName() + "@email.com");
        account.setPhoneNumber(faker.phoneNumber().phoneNumber());
        account.setDisabled(faker.bool().bool());
        account.setDateJoined(faker.date().birthday());
        return account;
    }
}

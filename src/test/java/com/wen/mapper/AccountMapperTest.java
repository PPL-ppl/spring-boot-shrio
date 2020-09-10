package com.wen.mapper;

import com.wen.entity.Account;
import com.wen.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountService accountService;

    @Test
    void test() {
        Account zs = accountService.findByUsername("zs");
        System.out.println(zs);
    }
}
package com.wen.service;

import com.wen.entity.Account;
import com.wen.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    Account findByUsername(String username);
}

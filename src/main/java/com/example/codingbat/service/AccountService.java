package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Account;
import com.example.codingbat.entity.Link;
import com.example.codingbat.repository.AboutRepository;
import com.example.codingbat.repository.AccountRepository;
import com.example.codingbat.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    //      Create
    public ApiResponse addAccount(Account account) {
        Account account1 = new Account();
        account1.setEmail(account.getEmail());
        account1.setPassword(account.getPassword());

        accountRepository.save(account1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    //    Get by id
    public Account getAccountById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent())
            return null;
        return optionalAccount.get();
    }

    //    Update
    public ApiResponse editAccount(Integer id, Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent())
            return new ApiResponse("Such Account was not found", false);
        Account editAccount = optionalAccount.get();
        editAccount.setEmail(account.getEmail());
        editAccount.setPassword(account.getPassword());

        accountRepository.save(editAccount);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteAccount(Integer id) {
        try {
            accountRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

package com.challenge.service;


import com.challenge.model.MoneyWallet;
import com.challenge.model.User;
import com.challenge.request.Transfer;


import java.util.List;

public interface CrudService<ID,T> {
    T create(User user);
    List<T> findAll();
    MoneyWallet transfer(Transfer transfer);
}

package com.challenge.service;


import com.challenge.model.DTO.UserDTO;
import com.challenge.model.MoneyWallet;
import com.challenge.request.Transfer;


import java.util.List;

public interface CrudService<ID,T> {
    T create(UserDTO user);

    List<T> findAll();
    MoneyWallet transfer(Transfer transfer);
}

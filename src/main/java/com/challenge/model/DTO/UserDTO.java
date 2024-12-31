package com.challenge.model.DTO;

import com.challenge.model.EROLE;
import com.challenge.model.MoneyWallet;
import com.challenge.model.User;

public record UserDTO(String username, String password, String cpf_cnpj, String email, MoneyWallet moneyWallet,
                      EROLE role) {
    public UserDTO(User user){
        this(user.getUsername(), user.getPassword(), user.getCpf_cnpj(), user.getEmail(), user.getMoneyWallet(),user.getRole());
    }
}

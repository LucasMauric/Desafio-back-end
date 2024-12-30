package com.challenge.service.impl;

import com.challenge.exceptions.UserNotFound;
import com.challenge.model.MoneyWallet;
import com.challenge.model.User;
import com.challenge.repository.MoneyRepository;
import com.challenge.repository.UserRepository;
import com.challenge.request.Transfer;
import com.challenge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final MoneyRepository moneyRepository;
    public UserServiceImpl(UserRepository userRepository,MoneyRepository moneyRepository){
        this.userRepository = userRepository;
        this.moneyRepository = moneyRepository;
    }

    @Override
    public User create(User user) {
        return  userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public MoneyWallet transfer(Transfer transfer) {

        //PAGADOR
        Optional<User> userPayer = userRepository.findById(transfer.getPayer());
        Optional<MoneyWallet> moneyWalletPayer = moneyRepository.findById(userPayer.get().getMoneyWallet().getId());
        logger.error("{}",moneyWalletPayer.get().getSaldo());
        if(moneyWalletPayer.get().getSaldo().compareTo(transfer.getValue()) == 1){
            BigDecimal saldoAtual = moneyWalletPayer.get().getSaldo().subtract(transfer.getValue());
            moneyWalletPayer.get().setSaldo(saldoAtual);
            moneyRepository.save(moneyWalletPayer.get());
        }else{
            throw new UserNotFound("Saldo insuficiente");
        }

        //BENEFICIARIO
        Optional<User> userPayee = userRepository.findById(transfer.getPayee());
        Optional<MoneyWallet> moneyWalletPayee = moneyRepository.findById(userPayee.get().getMoneyWallet().getId());
        BigDecimal novoSaldo = moneyWalletPayee.get().getSaldo().add(transfer.getValue());
        moneyWalletPayee.get().setSaldo(novoSaldo);
        moneyRepository.save(moneyWalletPayee.get());
        return moneyWalletPayee.get();

    }


}
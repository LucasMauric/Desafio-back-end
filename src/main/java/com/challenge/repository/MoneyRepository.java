package com.challenge.repository;

import com.challenge.model.MoneyWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyWallet,Long> {
}

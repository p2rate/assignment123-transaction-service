package com.ashkanzafari.assignment123.transactionservice.repository;

import com.ashkanzafari.assignment123.transactionservice.model.Balance;
import com.ashkanzafari.assignment123.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TransactionRepositoray.
 *
 * <p></p>
 */
@Repository
public interface BalanceRepositoray extends JpaRepository<Balance, Long> {

  Optional<Balance> findByAccountAndDeletedIsFalse(String toAccount);
}

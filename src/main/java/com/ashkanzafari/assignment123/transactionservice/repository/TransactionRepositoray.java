package com.ashkanzafari.assignment123.transactionservice.repository;

import com.ashkanzafari.assignment123.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionRepositoray.
 *
 * <p></p>
 */
@Repository
public interface TransactionRepositoray extends JpaRepository<Transaction, Long> {
}

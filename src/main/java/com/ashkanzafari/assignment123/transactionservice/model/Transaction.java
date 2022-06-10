package com.ashkanzafari.assignment123.transactionservice.model;

import com.ashkanzafari.assignment123.transactionservice.model.audit.Auditable;
import com.ashkanzafari.assignment123.transactionservice.model.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends Auditable {

  private String fromAccount;

  private String toAccount;

  @NotNull
  private TransactionType transactionType;

  @NotNull
  private Double amount;

  @JsonIgnore
  @ManyToMany(mappedBy = "transactions")
  private Set<Balance> balances = new HashSet<>();

  private Transaction(String fromAccount, String toAccount, TransactionType transactionType, Double amount) {

    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.transactionType = transactionType;
    this.amount = amount;
  }

  public static Transaction createTransferTransaction(String fromUser, String toUser, Double amount) {

    Transaction newTransaction = new Transaction(fromUser, toUser, TransactionType.TRANSFER, amount);
    return newTransaction;
  }

  public static Transaction createDepositTransaction(String toUser, Double amount) {

    Transaction newTransaction = new Transaction(null, toUser, TransactionType.DEPOSIT, amount);
    return newTransaction;
  }

  public static Transaction createWithDrawlTransaction(String fromUser, Double amount) {

    Transaction newTransaction = new Transaction(fromUser, null, TransactionType.WITHDRAWAL, amount);
    return newTransaction;
  }

}


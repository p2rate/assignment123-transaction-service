package com.ashkanzafari.assignment123.transactionservice.model;

import com.ashkanzafari.assignment123.transactionservice.model.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "account_index", columnList = "account", unique = true)
})
public class Balance extends Auditable {


  private String account;
  private Double balance;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Transaction> transactions = new HashSet<>();

  public Balance(String account, Double balance){
    this.account = account;
    this.balance = balance;
  }
}

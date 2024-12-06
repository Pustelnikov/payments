package dev.pustelnikov.payments.model.entity;

import dev.pustelnikov.payments.model.AccountCurrency;
import dev.pustelnikov.payments.model.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "account_number")
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_currency")
    private AccountCurrency accountCurrency;
    @Column(name = "account_balance")
    private BigDecimal accountBalance;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "users_user_id")
    @ToString.Exclude
    private UserEntity user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    @ToString.Exclude
    private List<TransactionEntity> accountTransactions;
}

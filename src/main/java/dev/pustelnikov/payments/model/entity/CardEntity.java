package dev.pustelnikov.payments.model.entity;

import dev.pustelnikov.payments.model.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_expiration_date")
    private LocalDate cardExpirationDate;
    @Column(name = "card_cvv")
    private Integer cardCvv;
    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private CardStatus cardStatus;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "accounts_account_id")
    @ToString.Exclude
    private AccountEntity account;
}

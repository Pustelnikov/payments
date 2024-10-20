package dev.pustelnikov.payments.repository;

import dev.pustelnikov.payments.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CardRepo extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findByCardNumber(String cardNumber);
}

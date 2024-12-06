package dev.pustelnikov.payments.repository;

import dev.pustelnikov.payments.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {
}

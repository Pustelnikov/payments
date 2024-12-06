package dev.pustelnikov.payments.repository;

import dev.pustelnikov.payments.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<AccountEntity, Long> {
}

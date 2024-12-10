package dev.pustelnikov.payments.repository;

import dev.pustelnikov.payments.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AccountRepo extends JpaRepository<AccountEntity, Long> {
    @Query("SELECT a FROM AccountEntity a WHERE a.user.userName LIKE %?1%")
    List<AccountEntity> findAccountsByUserName(String userName);
}

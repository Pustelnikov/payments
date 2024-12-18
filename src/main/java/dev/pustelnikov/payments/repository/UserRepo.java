package dev.pustelnikov.payments.repository;

import dev.pustelnikov.payments.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    @Query("SELECT u FROM UserEntity u WHERE CONCAT(u.userId, ' ', u.userName, ' ') LIKE %?1%")
    Page<UserEntity> search(String searchKeyword, Pageable pageable);
}

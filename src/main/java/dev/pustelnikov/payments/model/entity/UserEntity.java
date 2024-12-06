package dev.pustelnikov.payments.model.entity;

import dev.pustelnikov.payments.model.UserRole;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "users_user_id"))
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private Set<UserRole> userRoles;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @ToString.Exclude
    private List<AccountEntity> userAccounts;
}

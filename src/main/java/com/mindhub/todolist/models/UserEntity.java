package com.mindhub.todolist.models;

import com.mindhub.todolist.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired = true;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked = true;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired = true;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TaskEntity> taskEntityList;

    @Enumerated(EnumType.STRING)
    private RoleEnum rol;

    public UserEntity(String email) {
        this.email = email;
    }
}

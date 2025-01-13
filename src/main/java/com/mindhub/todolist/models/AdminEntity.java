package com.mindhub.todolist.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;

    public AdminEntity(String name, String lastname, UserEntity user) {
        this.name = name;
        this.lastname = lastname;
        this.user = user;
    }
}

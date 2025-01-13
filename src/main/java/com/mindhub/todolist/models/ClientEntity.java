package com.mindhub.todolist.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;

    public ClientEntity(String name, String lastname, UserEntity user) {
        this.name = name;
        this.lastname = lastname;
        this.user = user;
    }
}
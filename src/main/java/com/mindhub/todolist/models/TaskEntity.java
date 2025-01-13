package com.mindhub.todolist.models;

import com.mindhub.todolist.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated
    private Status status;
    @ManyToOne
    @JoinColumn(name = "UserEntity_id", nullable = false, foreignKey = @ForeignKey(name = "FK_UserEntityID"))
    private UserEntity userEntity;

    public TaskEntity(String title, String description, Status status, UserEntity userEntity) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.userEntity = userEntity;
    }
}

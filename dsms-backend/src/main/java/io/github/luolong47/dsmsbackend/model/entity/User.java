package io.github.luolong47.dsmsbackend.model.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Column(length = 20)
    private String role;
}

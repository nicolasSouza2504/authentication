package org.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "salt_password", columnDefinition = "TEXT")
    private String saltPassword;

    @Column(name = "user_name", columnDefinition = "TEXT")
    private String userName;

}

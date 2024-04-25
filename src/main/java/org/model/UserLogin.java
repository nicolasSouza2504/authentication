package org.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    private Long id;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "user_name", columnDefinition = "TEXT")
    private String userName;

}

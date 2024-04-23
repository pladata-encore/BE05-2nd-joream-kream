package com.example.springbootproject.auth.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "USERS")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME") @Setter
    private String username;

    @Column(name = "USER_ADDRESS") @Setter
    private String address;

    @Column(name = "USER_EMAIL") @Setter
    private String email;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_POINT")
    private Integer point;

    public void setPoint(Integer point) {
    this.point = point;
    }
}

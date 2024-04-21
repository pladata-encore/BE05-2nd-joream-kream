package com.example.springbootproject.sell.domain;

import com.example.springbootproject.auth.domain.Auth;
import com.example.springbootproject.size.domain.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "SELL")
public class Sell {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELL_ID")
    private Long id;
    @JoinColumn(name = "USER_ID")
    @OneToOne
    private Auth auth;
    @JoinColumn(name = "SIZE_ID")
    @ManyToOne
    private Size size;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "MATCH_YN")
    private Boolean matchYn;

}

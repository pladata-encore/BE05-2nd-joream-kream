package com.example.springbootproject.buy.domain;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.size.domain.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "BUYS")
public class Buy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUY_ID")
    private Long id;
    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;
    @JoinColumn(name = "SIZE_ID")
    @ManyToOne
    private Size size;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "CREATED_AT")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "MATCH_YN")
    private Boolean matchYn;
    @Column(name = "END_AT")
    private LocalDateTime endAt;


}

package com.example.springbootproject.sell.domain;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.size.domain.Size;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "SELLS")
public class Sell {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "SELL_ID")
    private Long id;

    @JoinColumn(name = "USER_ID")
    @ManyToOne // OneToOne에서 수정
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
    @Setter
    private Boolean matchYn;

    @Column(name = "END_AT")
    private LocalDateTime endAt;

}

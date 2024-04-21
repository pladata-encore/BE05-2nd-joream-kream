package com.example.springbootproject.point.domain;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.order.domain.OrderHistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//@Table(name = "USER")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINTHISTORY_ID")
    private Long id;

    @Column(name = "BALANCE")
    private Integer balance;

    @Column(name = "TRANSACTION_TYPE")
    private Boolean transactionType;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    @JoinColumn(name = "ORDER_HISTORY_ID")
    @OneToOne
    private OrderHistory orderHistory;
}

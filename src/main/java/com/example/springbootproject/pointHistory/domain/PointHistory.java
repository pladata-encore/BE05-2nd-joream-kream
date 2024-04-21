package com.example.springbootproject.pointHistory.domain;

import com.example.springbootproject.auth.domain.User;

import com.example.springbootproject.orderHistory.domain.OrderHistory;
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
@Table(name = "POINT_HISTORYS")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINTHISTORY_ID")
    private Long id;

    @Column(name = "POINTHISTORY_BALANCE")
    private Integer balance;

    @Column(name = "POINTHISTORY_TRANSACTION_TYPE")
    private Boolean transactionType;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    @JoinColumn(name = "ORDER_HISTORY_ID")
    @OneToOne
    private OrderHistory orderHistory;
}

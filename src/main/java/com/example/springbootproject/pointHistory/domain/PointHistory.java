package com.example.springbootproject.pointHistory.domain;

import com.example.springbootproject.auth.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Long balance;

    @Column(name = "POINTHISTORY_TRANSACTION_TYPE")
    private Boolean transactionType; //T면 입금 F면 출금

    @Column(name = "ORDER_CREATED_AT")
    private LocalDateTime createdAt;

//    @Column(name = "PRICE")
//    private Long price;

    @Column(name = "PRODUCTNAME")
    private String productName;

    @Column(name = "TRANSACTION_VOLUME")
    private Long transactionVolume;

    @Column(name = "SIZE")
    private String size;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setTransactionType(Boolean transactionType) {
        this.transactionType = transactionType;
    }
}

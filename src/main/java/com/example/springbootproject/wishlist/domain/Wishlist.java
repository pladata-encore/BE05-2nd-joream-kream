package com.example.springbootproject.wishlist.domain;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.size.domain.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "WISHLISTS")
public class Wishlist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WISHLIST_ID")
    private Long id;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    @JoinColumn(name = "SIZE_ID")
    @ManyToOne
    private Size size;

}

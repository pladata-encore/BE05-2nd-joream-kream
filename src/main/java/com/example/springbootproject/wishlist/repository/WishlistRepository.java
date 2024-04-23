package com.example.springbootproject.wishlist.repository;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.wishlist.domain.Wishlist;
import com.example.springbootproject.wishlist.dto.response.WishlistResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    List<Wishlist> findAllByUser_Id(Long userId);
    Optional<Wishlist> findByUserAndProduct(User user, Product product);
}

package com.example.springbootproject.wishlist.repository;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.wishlist.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    @Query("select distinct w from Wishlist w " +
            "join fetch w.user u " +
            "join fetch w.size s " +
            "where u.id = :userId")
    List<Wishlist> findAllByUser_Id(Long userId);
    Optional<Wishlist> findByUserAndSize(User user, Size size);
}

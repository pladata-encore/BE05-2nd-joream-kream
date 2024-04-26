package com.example.springbootproject.wishlist.controller;

import com.example.springbootproject.wishlist.dto.request.WishlistRequest;
import com.example.springbootproject.wishlist.dto.response.WishlistResponse;
import com.example.springbootproject.wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wishlists")
@AllArgsConstructor
public class WishlistController {
    private WishlistService wishlistService;

    @GetMapping("/info")
    public List<WishlistResponse> getAllWishlists(/*@PathVariable("userid") Long userid*/
            @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);

        return wishlistService.getAllWishlist(/*userid*/token);
    }

    @PostMapping
    public void addOrDeleteWishlist(@RequestHeader("Authorization") String bearerToken,
                            @RequestBody WishlistRequest request){
        String token = bearerToken.substring(7);
        wishlistService.addOrDeleteWishlist(token, request);
    }

}

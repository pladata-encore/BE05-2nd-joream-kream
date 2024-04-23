package com.example.springbootproject.wishlist.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.repository.ProductRepository;
import com.example.springbootproject.wishlist.domain.Wishlist;
import com.example.springbootproject.wishlist.dto.request.WishlistRequest;
import com.example.springbootproject.wishlist.dto.response.WishlistResponse;
import com.example.springbootproject.wishlist.exception.WishlistErrorCode;
import com.example.springbootproject.wishlist.exception.WishlistException;
import com.example.springbootproject.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;

    @Override
    public List<WishlistResponse> getAllWishlist(Long userid/*String token*/) {
//        Optional<Wishlist> byId = wishlistRepository.findById(uid);
//        Wishlist wishlist = byId.orElseThrow(() -> new IllegalArgumentException(".."));

        return wishlistRepository.findAllByUser_Id(userid).stream().map(WishlistResponse::from).toList();


    }

    @Override
    public void addOrDeleteWishlist(WishlistRequest request){
        // user 테이블에 user 가 있는지 확인
        Optional<User> uid = authRepository.findById(request.userId());
        User user = uid.orElseThrow(() -> new WishlistException(WishlistErrorCode.USER_NOT_FOUND));
        // product 테이블에 product 가 있는지 확인
        Optional<Product> pid = productRepository.findById(request.productId());
        Product product = pid.orElseThrow(() -> new WishlistException(WishlistErrorCode.PRODUCT_NOT_FOUND));

        // wishlist 에 user 와 product 가 존재하는지
        Optional<Wishlist> existWishlist = wishlistRepository.findByUserAndProduct(user, product);
        if(existWishlist.isPresent()){  // 존재하면 삭제
            wishlistRepository.delete(existWishlist.get());
        } else {    // 존재하지 않는다면 추가
            wishlistRepository.save(new Wishlist(null, user, product));
        }


    }
}

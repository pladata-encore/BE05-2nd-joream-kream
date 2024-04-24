package com.example.springbootproject.wishlist.service;

import com.example.springbootproject.auth.config.JwtTokenUtils;
import com.example.springbootproject.auth.config.TokenInfo;
import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.repository.ProductRepository;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.size.repository.SizeRepository;
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
    private final SizeRepository sizeRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public List<WishlistResponse> getAllWishlist(/*Long userid*/String token) {
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        return wishlistRepository.findAllByUser_Id(tokenInfo.id())
                .stream()
                .map(WishlistResponse::from)
                .toList();
    }

    @Override
    public void addOrDeleteWishlist(String token, WishlistRequest request){
        // 토큰으로 userID 를 가지고오기 위함
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);

        // user 테이블에 user 가 있는지 확인
        Optional<User> uid = authRepository.findById(tokenInfo.id());
        User user = uid.orElseThrow(() -> new WishlistException(WishlistErrorCode.USER_NOT_FOUND));

        // product 테이블에 product 가 있는지 확인
        Optional<Product> pid = productRepository.findById(request.productId());
        pid.orElseThrow(() -> new WishlistException(WishlistErrorCode.PRODUCT_NOT_FOUND));

        // request.sizeValue 와 request.productId 를 가지고 sizeId를 찾음
        Size size = sizeRepository.findBySizeValueAndProductId(request.sizeValue(), request.productId());

        // wishlist 에 user 와 size 가 존재하는지
        Optional<Wishlist> existWishlist = wishlistRepository.findByUserAndSize(user, size);
        if(existWishlist.isPresent()){  // 존재하면 삭제
            wishlistRepository.delete(existWishlist.get());
        } else {    // 존재하지 않는다면 추가
            wishlistRepository.save(new Wishlist(null, user, size));
        }


    }
}

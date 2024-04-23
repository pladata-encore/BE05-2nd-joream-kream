package com.example.springbootproject.wishlist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum WishlistErrorCode {

    USER_NOT_FOUND("해당 회원은 존재하지 않습니다",HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("해당 상품이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    PERMISSION_DENIED("권한이 없습니다",HttpStatus.FORBIDDEN);


    private final String description;
    private final HttpStatus httpStatus;
}

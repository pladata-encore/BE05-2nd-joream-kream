package com.example.springbootproject.global.exception;

import com.example.springbootproject.auth.excrption.AuthErrorResponse;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.buy.exception.BuyErrorResponse;
import com.example.springbootproject.buy.exception.BuyException;

import com.example.springbootproject.pointHistory.excrption.PointErrorResponse;
import com.example.springbootproject.pointHistory.excrption.PointException;
import com.example.springbootproject.product.exception.ProductErrorResponse;
import com.example.springbootproject.product.exception.ProductException;
import com.example.springbootproject.wishlist.exception.WishlistErrorResponse;
import com.example.springbootproject.wishlist.exception.WishlistException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<AuthErrorResponse> handleAuthException (AuthException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(AuthErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .errorMessage(e.getErrorMessage())
                        .build());
    }

    @ExceptionHandler(BuyException.class)
    public ResponseEntity<BuyErrorResponse> handleBuyException (BuyException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
            .body(BuyErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getErrorMessage())
                .build());
    }

    @ExceptionHandler(WishlistException.class)
    public ResponseEntity<WishlistErrorResponse> handleAuthException (WishlistException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(WishlistErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .errorMessage(e.getErrorMessage())
                        .build());
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductErrorResponse> handleAuthException (ProductException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ProductErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .errorMessage(e.getErrorMessage())
                        .build());
    }

    @ExceptionHandler(PointException.class)
    public ResponseEntity<PointErrorResponse> handleAuthException (PointException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(PointErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .errorMessage(e.getErrorMessage())
                        .build());
    }

    // @ExceptionHandler(BuyException.class)
    // public ResponseEntity<BuyErrorResponse> handleAuthException (BuyException e) {
    //     return ResponseEntity.status(e.getErrorCode().getHttpStatus())
    //             .body(BuyErrorResponse.builder()
    //                     .errorCode(e.getErrorCode())
    //                     .errorMessage(e.getErrorMessage())
    //                     .build());
    //
    // }

}

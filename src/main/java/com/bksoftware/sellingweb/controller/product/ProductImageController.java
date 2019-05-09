package com.bksoftware.sellingweb.controller.product;


import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.product.ProductImage;
import com.bksoftware.sellingweb.service_impl.product.ProductImageService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/image-product")
public class ProductImageController {

    @Autowired
    ProductImageService_Impl productImageService;

    @GetMapping
    public ResponseEntity<List<ProductImage>> findAllProductImage(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<ProductImage> productImages = productImageService.findAllProductImage();
            if (productImages != null) {
                return new ResponseEntity<>(productImages, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

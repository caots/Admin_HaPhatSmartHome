package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.product.ProductGuarantee;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/public/guarantees")
public class GuaranteeController {

    @Autowired
    ProductService_Impl productService;

    @GetMapping
    public ResponseEntity<List<ProductGuarantee>> findPhoneToGuarantee(
            @RequestParam("phone") long phone_number,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("access-control-allow-credentials" , "true");
        if (header.equals(Token.tokenHeader)) {
            List<ProductGuarantee> guarantees = productService.findGuaranteeToPhone(phone_number);
            return new ResponseEntity<>(guarantees, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.product.*;
import com.bksoftware.sellingweb.service_impl.product.BuyFormService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping("api/v1/admin")
public class AdminBuyFormController {
    private final BuyFormService_Impl buyFormService;
    private final ProductService_Impl productService;

    public AdminBuyFormController(BuyFormService_Impl buyFormService, ProductService_Impl productService) {
        this.buyFormService = buyFormService;
        this.productService = productService;
    }

    @GetMapping("/uncheck-buy-form")
    public ResponseEntity<List<BuyForm>> findAllUncheckBuyForm() {
        if (buyFormService.findAllUnCheckBuyForm() != null) {
            return new ResponseEntity<>(buyFormService.findAllUnCheckBuyForm(), HttpStatus.OK);
        } else return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/check-buy-form", params = "buy-form-id")
    public ResponseEntity<String> checkBuyForm(@RequestParam(name = "buy-form-id") int id) {
        BuyForm buyForm = buyFormService.findById(id);
        List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProductByBuyFormId(id);
        if (buyFormHasProducts.isEmpty()) return new ResponseEntity<>("no product", HttpStatus.BAD_REQUEST);
        buyFormHasProducts.forEach(buyFormHasProduct -> {
            buyFormHasProduct.setSoldDate(LocalDate.now());
            buyFormService.updateBuyFormHasProduct(buyFormHasProduct);
        });
        if (!buyFormService.checkBuyForm(buyForm))
            return new ResponseEntity<>("check fail", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("check success", HttpStatus.OK);
    }

    @GetMapping(value = "/products-in-buy-form", params = "buy-form-id")
    public ResponseEntity<List<BuyFormDetail>> findAllBuyFormDetailByBuyForm(@RequestParam(value = "buy-form-id") int id) {
        List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProductByBuyFormId(id);
        List<BuyFormDetail> buyFormDetails = new ArrayList<>();
        buyFormHasProducts.forEach(buyFormHasProduct -> {
            BuyFormDetail buyFormDetail = BuyFormDetail.builder()
                    .product(productService.findById(buyFormHasProduct.getProductId()))
                    .quantity(buyFormHasProduct.getQuantity())
                    .soldDate(buyFormHasProduct.getSoldDate()).build();
            buyFormDetails.add(buyFormDetail);
        });
        if (buyFormDetails.isEmpty()) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(buyFormDetails, HttpStatus.OK);
    }
}

package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.RequestPrice;
import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.RequestPriceService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "api/v1/admin/request-price")
public class AdminRequestPriceController {

    @Autowired
    private RequestPriceService_Impl requestPriceService;

    //add
    @RolesAllowed("ADMIN")
    @PostMapping
    public ResponseEntity<Object> addRequestPrice(@RequestBody RequestPrice requestPrice) {
        requestPrice.setStatus(true);
        boolean result = requestPriceService.saveRequestPrice(requestPrice);
        if (result) return new ResponseEntity<>(requestPrice, HttpStatus.OK);

        return new ResponseEntity<>("create Request-Price fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") int id) {

        boolean result = requestPriceService.deleteRequestPrice(id);
        if (result) return new ResponseEntity<>("delete Request-Price success", HttpStatus.OK);
        return new ResponseEntity<>("delete Request-Price fail", HttpStatus.BAD_REQUEST);
    }

}

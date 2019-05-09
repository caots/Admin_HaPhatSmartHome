package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.RequestPrice;

import com.bksoftware.sellingweb.service_impl.RequestPriceService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/request-price")
public class RequestPriceController {

    @Autowired
    private RequestPriceService_Impl requestPriceService;

    @GetMapping
    public ResponseEntity<List<RequestPrice>> findAllRequestPrice(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);

            List<RequestPrice> requestPrices = requestPriceService.findByStatus(pageable).getContent();
            if (requestPrices != null) {
                return new ResponseEntity<>(requestPrices, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page")
    public ResponseEntity<Double> findAllRequestPricePage(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");if (header.equals(Token.tokenHeader)) {
            List<RequestPrice> requestPrices = requestPriceService.findByStatusPage();
            float page = requestPrices.size();
            double result = Math.ceil(page / 10) + 1;
            if ((page / 10) % 2 == 0 && (page / 10) != 0) {
                result -= 1;
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<RequestPrice> findAllRequestPricePage(
            HttpServletResponse response
            , @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");if (header.equals(Token.tokenHeader)) {
            RequestPrice requestPrice = requestPriceService.findById(id);
            return new ResponseEntity<>(requestPrice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

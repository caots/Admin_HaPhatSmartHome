package com.bksoftware.sellingweb.controller.image;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.homepage.HomeImage;
import com.bksoftware.sellingweb.service_impl.homepage.HomeImageService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/image")
public class homeImageController {

    @Autowired
    private HomeImageService_Impl homeImageService;

    @GetMapping("home-page")
    public ResponseEntity<List<HomeImage>> findAllHomeImage(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(homeImageService.getHomeImage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

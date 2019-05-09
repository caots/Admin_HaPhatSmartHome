package com.bksoftware.sellingweb.controller.news;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.news.NewsImage;
import com.bksoftware.sellingweb.service_impl.news.NewsImageService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/image-news")
public class NewsImageController {

    @Autowired
    NewsImageService_Impl newsImageService;

    @GetMapping
    public ResponseEntity<List<NewsImage>> findAllNewsImage(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<NewsImage> newsImages = newsImageService.findAllNewsImage();
            if (newsImages != null) return new ResponseEntity<>(newsImages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

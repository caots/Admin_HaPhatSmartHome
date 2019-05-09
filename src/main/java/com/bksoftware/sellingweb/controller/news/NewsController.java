package com.bksoftware.sellingweb.controller.news;


import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.news.News;
import com.bksoftware.sellingweb.service_impl.news.NewsService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public/news")
public class NewsController {

    @Autowired
    NewsService_Impl newsService;

    @GetMapping
    public ResponseEntity<List<News>> findAllNews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<News> newsList = newsService.findAllNews(pageable).getContent();
            newsList.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
            if (newsList != null) {
                newsList = newsService.findAllNewsByTime();
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/views")
    public ResponseEntity<List<News>> findAllNewsByViews(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<News> newsList = newsService.findAllNewsByViews();
            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time")
    public ResponseEntity<List<News>> findAllNewsByTime(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<News> newsList = newsService.findAllNewsByTime();
            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/topic")
    public ResponseEntity<List<News>> findAllNewsByTopic(
            @RequestParam("topic-name") String nameTopic,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<News> newsList = newsService.findAllNewsByTopic(nameTopic, pageable).getContent();
            newsList.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
            if (newsList != null) {
                newsList = newsService.findAllNewsByTime();

                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

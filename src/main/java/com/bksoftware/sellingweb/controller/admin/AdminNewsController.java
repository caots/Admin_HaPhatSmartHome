package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.news.News;
import com.bksoftware.sellingweb.entities.news.NewsImage;
import com.bksoftware.sellingweb.entities.news.Topic;
import com.bksoftware.sellingweb.service_impl.news.NewsImageService_Impl;
import com.bksoftware.sellingweb.service_impl.news.NewsService_Impl;
import com.bksoftware.sellingweb.service_impl.news.TopicService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin")
public class AdminNewsController {

    @Autowired
    TopicService_Impl topicService;

    @Autowired
    NewsService_Impl newsService;

    @Autowired
    NewsImageService_Impl newsImageService;

    //=============================== Topic =================================
    @RolesAllowed("ADMIN")
    @PostMapping("/topic")
    public ResponseEntity<Object> createTopic(@RequestBody Topic topic) {
        topic.setStatus(true);
        if (topicService.saveTopic(topic))
            return new ResponseEntity<>("create success", HttpStatus.OK);
        else
            return new ResponseEntity<>("create fail", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/topic")
    public ResponseEntity<Object> updateTopic(@RequestBody Topic topic) {
        if (topicService.saveTopic(topic))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fail", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/delete-topic")
    public ResponseEntity<Object> deleteTopic(@RequestBody Topic topic) {
        if (topicService.deleteTopic(topic))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
    }

    //=============================== News ==================================

    @RolesAllowed("ADMIN")
    @PostMapping("/news")
    public ResponseEntity<Object> createNews(
            @RequestBody News news,
            @RequestParam(name = "topic-id") int topicId
    ) {
        news.setStatus(true);
        news.setTopic(topicService.findById(topicId));
        if (newsService.saveNews(news))
            return new ResponseEntity<>("create success", HttpStatus.OK);
        else
            return new ResponseEntity<>("create fail", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/news")
    public ResponseEntity<Object> updateNews(@RequestBody News news) {
        if (newsService.saveNews(news))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fail", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/delete-news")
    public ResponseEntity<Object> deleteNews(@RequestBody News news) {
        if (newsService.deleteNews(news))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
    }

    //=============================== Image News ============================

    @RolesAllowed("ADMIN")
    @PostMapping("/image-news")
    public ResponseEntity<Object> createImageNews(
            @RequestBody NewsImage newsImage,
            @RequestParam(name = "news-id") int newsId
    ) {
        newsImage.setStatus(true);
        newsImage.setNews(newsService.findById(newsId));
        if (newsImageService.saveNewsImage(newsImage))
            return new ResponseEntity<>("create success", HttpStatus.OK);
        else
            return new ResponseEntity<>("create fail", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/image-news")
    public ResponseEntity<Object> updateImageNews(@RequestBody NewsImage newsImage) {
        if (newsImageService.saveNewsImage(newsImage))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fail", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/delete-image-news")
    public ResponseEntity<Object> deleteImagesNews(@RequestBody NewsImage newsImage) {
        if (newsImageService.deleteNewsImage(newsImage))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
    }


}

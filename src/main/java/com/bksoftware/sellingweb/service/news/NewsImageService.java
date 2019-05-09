package com.bksoftware.sellingweb.service.news;

import com.bksoftware.sellingweb.entities.news.NewsImage;

import java.util.List;

public interface NewsImageService {

    List<NewsImage> findAllNewsImage();

    NewsImage findById(int id);

    boolean saveNewsImage(NewsImage newsImage);

    boolean deleteNewsImage(NewsImage newsImage);


}

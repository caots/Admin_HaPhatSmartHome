package com.bksoftware.sellingweb.service.news;

import com.bksoftware.sellingweb.entities.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    Page<News> findAllNews(Pageable pageable);

    List<News> findAllNewsByViews();

    List<News> findAllNewsByTime();

    Page<News> findAllNewsByTopic(String nameTopic, Pageable pageable);

    News findById(int id);

    boolean saveNews(News news);

    boolean deleteNews(News news);

}

package com.bksoftware.sellingweb.service_impl.news;

import com.bksoftware.sellingweb.entities.news.News;
import com.bksoftware.sellingweb.repository.news.NewsRepository;
import com.bksoftware.sellingweb.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class NewsService_Impl implements NewsService {

    Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    NewsRepository newsRepository;

    @Override
    public Page<News> findAllNews(Pageable pageable) {
        try {
            return newsRepository.findAllNews(pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findAllNewsByViews() {
        try {
            List<News> newsList = newsRepository.findAll();

            newsList.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList())
                    .sort((c1, c2) -> c2.getView() - c1.getView());
            return newsList;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-views-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findAllNewsByTime() {
        try {
            List<News> newsList = newsRepository.findAll();
            LocalDateTime localTime_n1 = LocalDateTime.now();
            LocalDateTime localTime_n2 = LocalDateTime.now();
            newsList.sort((n1, n2) -> -(int) ChronoUnit.MINUTES.between(n1.getTime(), localTime_n1)
                    + (int) ChronoUnit.HOURS.between(n2.getTime(), localTime_n2));
            newsList.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
            return newsList;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-views-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<News> findAllNewsByTopic(String nameTopic, Pageable pageable) {
        try {
            return newsRepository.findAllNewsByTopic(nameTopic, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-topic-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public News findById(int id) {
        try {
            News news = newsRepository.findById(id);
            if (news.isStatus()) return news;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-news-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveNews(News news) {
        try {
            news.setTime(LocalDateTime.now());
            newsRepository.save(news);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-news-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNews(News news) {
        try {
            news.setStatus(false);
            newsRepository.save(news);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-news-error : {0}", ex.getMessage());
        }
        return false;
    }

}

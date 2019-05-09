package com.bksoftware.sellingweb.service_impl.news;

import com.bksoftware.sellingweb.entities.news.NewsImage;
import com.bksoftware.sellingweb.repository.news.NewsImageRepository;
import com.bksoftware.sellingweb.service.news.NewsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class NewsImageService_Impl implements NewsImageService {

    Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    NewsImageRepository newsImageRepository;

    @Override
    public List<NewsImage> findAllNewsImage() {
        try {
            List<NewsImage> newsImages = newsImageRepository.findAll();
            return newsImages
                    .stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-image-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public NewsImage findById(int id) {
        try {
            NewsImage newsImage = newsImageRepository.findById(id);
            if (newsImage.isStatus() == true) return newsImage;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-news-image-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveNewsImage(NewsImage newsImage) {
        try {
            newsImageRepository.save(newsImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-news-image-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNewsImage(NewsImage newsImage) {
        try {
            newsImage.setStatus(false);
            newsImageRepository.save(newsImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-news-image-error : {0}", ex.getMessage());
        }
        return false;
    }
}

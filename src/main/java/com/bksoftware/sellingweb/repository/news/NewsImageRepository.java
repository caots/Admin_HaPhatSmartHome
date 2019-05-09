package com.bksoftware.sellingweb.repository.news;

import com.bksoftware.sellingweb.entities.news.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsImageRepository extends JpaRepository<NewsImage, Integer> {

    NewsImage findById(int id);
}

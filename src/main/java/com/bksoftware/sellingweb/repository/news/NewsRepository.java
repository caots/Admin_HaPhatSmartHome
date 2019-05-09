package com.bksoftware.sellingweb.repository.news;

import com.bksoftware.sellingweb.entities.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("select s from News s")
    Page<News> findAllNews(Pageable pageable);

    @Query("select  s from  News s join s.topic t where  t.name = :nameTopic")
    Page<News> findAllNewsByTopic(@Param("nameTopic") String nameTopic, Pageable pageable);

    News findById(int id);
}

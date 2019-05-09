package com.bksoftware.sellingweb.repository.news;

import com.bksoftware.sellingweb.entities.news.Topic;
import com.bksoftware.sellingweb.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {

    Topic findById(int id);

}

package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    List<Feedback> findAllByProductDetails(ProductDetails productDetails);
    Feedback findById(int id);
}

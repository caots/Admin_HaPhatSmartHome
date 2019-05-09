package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;

import java.util.List;


public interface FeedbackService {
    List<Feedback> findAllFeedbackByProduct(ProductDetails productDetails);

    Feedback findById(int id);

    boolean saveFeedback(Feedback feedback);

    boolean deleteFeedback(Feedback feedback);
}


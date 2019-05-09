package com.bksoftware.sellingweb.service_impl.product;


import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.repository.product.FeedbackRepository;
import com.bksoftware.sellingweb.repository.product.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.repository.product.ReplyRepository;
import com.bksoftware.sellingweb.service.product.FeedbackService;
import com.bksoftware.sellingweb.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FeedbackService_Impl implements FeedbackService {

    private final static Logger LOGGER = Logger.getLogger(FeedbackService_Impl.class.getName());

    private final
    FeedbackRepository feedbackRepository;

    private final
    ReplyRepository replyRepository;
    private final RepLyService_Impl repLyService;

    public FeedbackService_Impl(FeedbackRepository feedbackRepository, ReplyRepository replyRepository, RepLyService_Impl repLyService) {
        this.feedbackRepository = feedbackRepository;
        this.replyRepository = replyRepository;
        this.repLyService = repLyService;
    }

    @Override
    public List<Feedback> findAllFeedbackByProduct(ProductDetails productDetails) {
        try {
            List<Feedback> feedbacks = feedbackRepository.findAllByProductDetails(productDetails);
            return feedbacks
                    .stream()
                    .filter(Feedback::isStatus)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-feedback-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Feedback findById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public boolean saveFeedback(Feedback feedback) {
        try {
            feedback.setTime(LocalDateTime.now());
            feedbackRepository.save(feedback);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-feedback-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteFeedback(Feedback feedback) {
        try {
            feedback.setStatus(false);
            feedbackRepository.save(feedback);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-feedback-error : {0}", ex.getMessage());
        }
        return false;
    }


}

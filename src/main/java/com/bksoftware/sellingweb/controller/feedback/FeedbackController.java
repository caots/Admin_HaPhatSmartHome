package com.bksoftware.sellingweb.controller.feedback;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.service_impl.product.FeedbackService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductDetailsService_Impl;
import com.bksoftware.sellingweb.service_impl.product.RepLyService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("api/v1/public/feedback")
public class FeedbackController {


    private final
    FeedbackService_Impl feedbackService;
    private final ProductDetailsService_Impl productDetailsService;
    private final RepLyService_Impl repLyService;

    public FeedbackController(FeedbackService_Impl feedbackService, ProductDetailsService_Impl productDetailsService, RepLyService_Impl repLyService) {
        this.feedbackService = feedbackService;
        this.productDetailsService = productDetailsService;
        this.repLyService = repLyService;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> findAllFeedbackByProductDetails(
            @RequestParam("product-details-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Feedback> feedback = feedbackService
                    .findAllFeedbackByProduct(productDetailsService.findById(id));
            if (feedback != null) {
                return new ResponseEntity<>(feedback, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> countAllFeedbackByProductDetails(
            @RequestParam("product-details-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Feedback> feedbacks = feedbackService.findAllFeedbackByProduct(productDetailsService.findById(id));
            AtomicInteger count = new AtomicInteger(feedbacks.size());
            feedbacks.forEach(feedback -> repLyService.findAllRepliesByFeedBack(feedback).forEach(reply -> count.getAndIncrement()));

            if (count.get() >= 0) {
                return new ResponseEntity<>(count.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody Feedback feedback,
            @RequestParam(name = "product-details-id") int productDetailsId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            feedback.setProductDetails(productDetailsService.findById(productDetailsId));
            feedback.setStatus(true);
            if (feedbackService.saveFeedback(feedback))
                return new ResponseEntity<>("add success", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @GetMapping(value = "/cookie")
//    public ResponseEntity<Object> findAllFeedbackCookie(HttpServletRequest request) {
//
//        Map<String, String> feedbackMap = new HashMap<>();
//        Cookie cookie = null;
//        Cookie[] cookies = null;
//        // Get an array of Cookies associated with this domain
//        cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie value : cookies) {
//                cookie = value;
//                feedbackMap.put(cookie.getName(), cookie.getValue());
//            }
//        } else {
//            System.out.println("No Cookie here");
//            return new ResponseEntity<Object>("No Cookie Here", HttpStatus.OK);
//        }
//        return new ResponseEntity<>(cookies, HttpStatus.OK);
//    }
}

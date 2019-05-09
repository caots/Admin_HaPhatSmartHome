package com.bksoftware.sellingweb.service_impl.product;


import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.Reply;
import com.bksoftware.sellingweb.repository.product.ReplyRepository;
import com.bksoftware.sellingweb.service.product.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RepLyService_Impl implements ReplyService {

    private static final Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());

    @Autowired
    ReplyRepository replyRepository;


    @Override
    public List<Reply> findAllRepliesByFeedBack(Feedback feedback) {
        try {
            List<Reply> replies = replyRepository.findAllByFeedback(feedback);
            return replies
                    .stream()
                    .filter(p -> (p.isStatus()))
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-reply-error : {0}");
        }
        return null;
    }


    @Override
    public Reply saveReply(Reply reply) {
        try {
            reply.setTime(LocalDateTime.now());
            return replyRepository.save(reply);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-reply-error : {0}", ex.getMessage());
        }
        return null;
    }

}

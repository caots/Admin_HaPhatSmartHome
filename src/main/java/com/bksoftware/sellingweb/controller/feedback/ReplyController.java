package com.bksoftware.sellingweb.controller.feedback;


import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.product.Reply;
import com.bksoftware.sellingweb.service_impl.product.FeedbackService_Impl;
import com.bksoftware.sellingweb.service_impl.product.RepLyService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api/v1/public/replies")
public class ReplyController {

    @Autowired
    RepLyService_Impl repLyService;

    @Autowired
    private FeedbackService_Impl feedbackService;

    @GetMapping
    public ResponseEntity<List<Reply>> findAllReplies(
            @RequestParam(name = "feedback-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Reply> replies = repLyService.findAllRepliesByFeedBack(feedbackService.findById(id));

            if (replies == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(replies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Object> saveReply(
            @RequestBody Reply reply,
            HttpServletRequest request,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            if (reply != null) {
                if (request.getUserPrincipal() != null && !request.getUserPrincipal().getName().equals("")) {
                    reply.setRole("ADMIN");
                    return new ResponseEntity<>(repLyService.saveReply(reply), HttpStatus.OK);
                } else {
                    reply.setRole("VIEWER");
                    return new ResponseEntity<>(repLyService.saveReply(reply), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

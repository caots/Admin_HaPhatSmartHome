package com.bksoftware.sellingweb.service.product;


import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.Reply;

import java.util.List;

public interface ReplyService {

    List<Reply> findAllRepliesByFeedBack(Feedback feedback);

    Reply saveReply(Reply reply);
}

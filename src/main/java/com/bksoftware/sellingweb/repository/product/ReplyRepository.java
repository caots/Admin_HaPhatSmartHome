package com.bksoftware.sellingweb.repository.product;


import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findAllByFeedback(Feedback feedback);
}

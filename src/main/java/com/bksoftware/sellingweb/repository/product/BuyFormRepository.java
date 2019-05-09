package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyFormRepository extends JpaRepository<BuyForm, Integer> {

    List<BuyForm> findAllByCheckedAndStatus(boolean checked,boolean status);

    List<BuyForm> findAllByPhoneNumber(long phone_number);

    BuyForm findById(int id);


}

package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyFormHasProductRepository extends JpaRepository<BuyFormHasProduct, Integer> {

    Page<BuyFormHasProduct> findByStatus(Pageable pageable, boolean status);

    @Query(value = "select * from buy_form_has_product bf where bf.buy_form_id = ?1 and  bf.product_id= ?2", nativeQuery = true)
    BuyFormHasProduct findByBuyFormIdAndProductIdCart(int buyFormId, int productId);

//    BuyFormHasProduct findByBuyFormIdAndProductIdCart(@Param("buyFormId") int buyFormId,@Param("productId")  int productId);

    List<BuyFormHasProduct> findByBuyFormId(int buyFormId);


    BuyFormHasProduct findById(int id);

    @Query("select bf from BuyFormHasProduct bf where bf.status=true")
    List<BuyFormHasProduct> findByStatusPage(boolean status);
}

package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import com.bksoftware.sellingweb.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuyFormService {

    Page<BuyFormHasProduct> findAllBuyFormHasProduct(Pageable pageable);

    List<BuyForm> findAllUnCheckBuyForm();

    boolean checkBuyForm(BuyForm buyForm);

    BuyForm findById(int id);

    BuyFormHasProduct findBuyFormHasProductById(int id);

    boolean saveBuyForm(BuyForm buyForm);

    List<BuyFormHasProduct> findAllBuyFormHasProductByBuyFormId(int id);

     List<BuyFormHasProduct> findAllBuyFormHasProductPage();

    BuyFormHasProduct findByBuyFormAndProduct(BuyForm buyForm, Product product);

    boolean updateBuyFormHasProduct(BuyFormHasProduct buyFormHasProduct);

    boolean deleteBuyFormHasProduct(int id);
}

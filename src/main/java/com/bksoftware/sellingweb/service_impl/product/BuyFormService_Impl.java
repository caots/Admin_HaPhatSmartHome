package com.bksoftware.sellingweb.service_impl.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.repository.product.BuyFormHasProductRepository;
import com.bksoftware.sellingweb.repository.product.BuyFormRepository;
import com.bksoftware.sellingweb.service.product.BuyFormService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BuyFormService_Impl implements BuyFormService {

    private final static Logger LOGGER = Logger.getLogger(FeedbackService_Impl.class.getName());

    private final BuyFormRepository buyFormRepository;

    private final BuyFormHasProductRepository buyFormHasProductRepository;

    public BuyFormService_Impl(BuyFormRepository buyFormRepository, BuyFormHasProductRepository buyFormHasProductRepository) {
        this.buyFormRepository = buyFormRepository;
        this.buyFormHasProductRepository = buyFormHasProductRepository;
    }

    @Override
    public Page<BuyFormHasProduct> findAllBuyFormHasProduct(Pageable pageable) {
        return buyFormHasProductRepository.findByStatus(pageable, true);
    }


    @Override
    public List<BuyFormHasProduct> findAllBuyFormHasProductPage() {
        return buyFormHasProductRepository.findByStatusPage(true);
    }

    @Override
    public List<BuyForm> findAllUnCheckBuyForm() {
        try {
            return buyFormRepository.findAllByCheckedAndStatus(false, true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-uncheck-buyForm-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkBuyForm(BuyForm buyForm) {
        try {
            buyForm.setChecked(true);
            buyFormRepository.save(buyForm);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-uncheck-buyForm-error {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public BuyForm findById(int id) {
        try {
            return buyFormRepository.findById(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-buyForm-error {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public BuyFormHasProduct findBuyFormHasProductById(int id) {
        try {
            BuyFormHasProduct buyFormHasProduct = buyFormHasProductRepository.findById(id);
            if (buyFormHasProduct.getStatus()) return buyFormHasProduct;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-buyForm-has-product-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveBuyForm(BuyForm buyForm) {
        try {
            buyFormRepository.save(buyForm);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-buy-form-error: {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<BuyFormHasProduct> findAllBuyFormHasProductByBuyFormId(int id) {
        try {
            return buyFormHasProductRepository.findByBuyFormId(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buy-form-has-product-error: {0}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public BuyFormHasProduct findByBuyFormAndProduct(BuyForm buyForm, Product product) {
        try {
            BuyFormHasProduct buyFormHasProduct = buyFormHasProductRepository.findByBuyFormIdAndProductIdCart(buyForm.getId(), product.getId());
            return buyFormHasProduct;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-buy-form-has-product-error: {0}", ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateBuyFormHasProduct(BuyFormHasProduct buyFormHasProduct) {
        try {
            buyFormHasProductRepository.save(buyFormHasProduct);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "update-buy-form-has-product-error: {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBuyFormHasProduct(int id) {
        try {
            List<BuyFormHasProduct> buyFormHasProducts = buyFormHasProductRepository.findByBuyFormId(id);
            buyFormHasProducts.forEach(bf -> {
                bf.setStatus(false);
                buyFormHasProductRepository.save(bf);
            });

            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-buy-form-has-product-error: {0}", ex.getMessage());
            return false;
        }
    }
}

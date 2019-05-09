package com.bksoftware.sellingweb.service_impl.product;

import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.entities.product.ProductImage;
import com.bksoftware.sellingweb.repository.product.FeatureRepository;
import com.bksoftware.sellingweb.repository.product.FeedbackRepository;
import com.bksoftware.sellingweb.repository.product.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.product.ProductImageRepository;
import com.bksoftware.sellingweb.service.product.ProductDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductDetailsService_Impl implements ProductDetailsService {

    private final static Logger LOGGER = Logger.getLogger(ProductDetailsService_Impl.class.getName());

    private final ProductDetailsRepository productDetailsRepository;
    private final FeatureRepository featureRepository;
    private final ProductImageRepository productImageRepository;

    public ProductDetailsService_Impl(ProductDetailsRepository productDetailsRepository, FeatureRepository featureRepository, FeedbackRepository feedbackRepository, ProductImageRepository productImageRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.featureRepository = featureRepository;
        this.productImageRepository = productImageRepository;
    }


    public Page<ProductDetails> findDetailsProductByName(String name, Pageable pageable) {
        try {
            return productDetailsRepository.findByName(name, pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-details-product-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    public List<ProductDetails> findDetailsProductByNamePage(String name) {
        try {
            return productDetailsRepository.findByNamePage(name);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-details-product-by-name-page-error : {0}", ex.getMessage());
        }
        return null;
    }



    public List<ProductDetails> findAllDePro() {
        return productDetailsRepository.findAllDePro();
    }

    @Override
    public List<ProductDetails> findAll() {
        return productDetailsRepository.findAll();
    }


    @Override
    public Page<ProductDetails> findAllProductDetails(Pageable pageable) {
        return productDetailsRepository.findByStatus(true, pageable);
    }

    @Override
    public boolean saveProductDetails(ProductDetails productDetails) {
        try {
            productDetailsRepository.save(productDetails);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-productDetails-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProductDetails(ProductDetails productDetails) {
        try {
            productDetails.setStatus(false);
            productDetailsRepository.save(productDetails);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-productDetails-error", ex.getMessage());
            return false;
        }
    }


    @Override
    public ProductDetails findById(int id) {
        ProductDetails productDetails = productDetailsRepository.findById(id);
        if (productDetails.isStatus()) return productDetails;
        return null;
    }

    @Override
    public List<Feature> showFeatureById(int id) {
        try {
            return featureRepository.showFeatureById(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-feature-error", ex.getMessage());
        }
        return null;
    }

    @Override
    public ProductDetails showProductDetails(int id) {

        try {
            return productDetailsRepository.showProductDetailsById(id);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show product-details-error", ex.getMessage());
        }
        return null;
    }


    @Override
    public boolean saveFeature(Feature feature) {
        try {
            featureRepository.save(feature);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-feature-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteFeature(Feature feature) {
        try {
            feature.setStatus(false);
            featureRepository.save(feature);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-feature-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveProductImage(ProductImage productImage) {
        try {
            productImage.setStatus(true);
            productImageRepository.save(productImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-image-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProductImage(ProductImage productImage) {
        try {
            productImage.setStatus(false);
            productImageRepository.save(productImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-image-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public ProductDetails findByProduct(Product product) {
        try {
            return productDetailsRepository.findByProduct(product);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-details-error : {0}", ex.getMessage());
            return null;
        }
    }

}

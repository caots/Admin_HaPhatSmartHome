package com.bksoftware.sellingweb.service_impl.product;


import com.bksoftware.sellingweb.entities.product.ProductImage;
import com.bksoftware.sellingweb.repository.product.ProductImageRepository;
import com.bksoftware.sellingweb.service.product.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductImageService_Impl implements ProductImageService {

    private static final Logger LOGGER = Logger.getLogger(ProductImageService_Impl.class.getName());

    @Autowired
    ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> findAllProductImage() {
        try {
            List<ProductImage> productImages = productImageRepository.findAll();
            return productImages
                    .stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "fin-all-product-image-error : {0}", ex.getMessage());
        }
        return null;
    }
}

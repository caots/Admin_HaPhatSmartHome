package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
}

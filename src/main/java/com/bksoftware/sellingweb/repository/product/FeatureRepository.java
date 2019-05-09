package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Integer> {
    @Query("select f from Feature f where f.status=true and f.productDetails.id= :idFeature")
    public List<Feature> showFeatureById(@Param("idFeature") int idFeature );

}

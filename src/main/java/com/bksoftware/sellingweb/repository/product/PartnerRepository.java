package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    Partner findByName(String name);

    Partner findById(int id);

    List<Partner> findByStatus(boolean status);


    @Query("select p from Partner p where p.status=true")
    Page<Partner> findAllPartner(Pageable pageable);

}

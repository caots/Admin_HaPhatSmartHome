package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.RequestPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestPriceRepository extends JpaRepository<RequestPrice, Integer> {

    Page<RequestPrice> findByStatus(Pageable pageable, boolean status);

    List<RequestPrice> findByStatus(boolean status);

    RequestPrice findById(int id);

}

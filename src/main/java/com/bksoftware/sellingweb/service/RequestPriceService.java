package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.RequestPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestPriceService {

    Page<RequestPrice> findByStatus(Pageable pageable);

    List<RequestPrice> findByStatusPage();

    RequestPrice findById(int id);

    boolean saveRequestPrice(RequestPrice requestPrice);

    boolean deleteRequestPrice(int id);
}

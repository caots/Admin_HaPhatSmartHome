package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.product.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public interface PartnerService {

    List<Partner> findAllPartnerPage();

    Partner findByName(String name);

    boolean savePartner(Partner partner);

    boolean deletePartner(Partner partner);

    Partner findById(int id);

    Page<Partner> findAllPartner(Pageable pageable);


}

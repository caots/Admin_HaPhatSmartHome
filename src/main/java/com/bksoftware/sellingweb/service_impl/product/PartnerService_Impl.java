package com.bksoftware.sellingweb.service_impl.product;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.repository.product.PartnerRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.service.product.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PartnerService_Impl implements PartnerService {

    @Autowired
    private ProductRepository productRepository;

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    private final PartnerRepository partnerRepository;



    public PartnerService_Impl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }



    @Override
    public Partner findByName(String name) {
        Partner partner = partnerRepository.findByName(name);
        if (partner.isStatus() == true) return partner;
        return null;
    }

    @Override
    public boolean savePartner(Partner partner) {
        try {
            partnerRepository.save(partner);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-partner-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePartner(Partner partner) {
        try {
            partner.setStatus(false);
            partnerRepository.save(partner);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-partner-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public Partner findById(int id) {
        Partner partner = partnerRepository.findById(id);
        if (partner.isStatus() == true) return partner;
        return null;
    }

    @Override
    public Page<Partner> findAllPartner(Pageable pageable) {
        try {
            return partnerRepository.findAllPartner(pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-partner-error", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Partner> findAllPartnerPage() {
        try {
            return partnerRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-partner-page-error", ex.getMessage());
        }
        return null;
    }


    /*@Override
    public HashSet<String> showPartByBigCategory(int id) {
        List<Partner> lstPartner=  partnerRepository.findAll();
        List<Product> lstProduct = (List<Product>) productRepository.showProductByBigList(id);
        HashMap<Integer,Partner> lstPartId = new HashMap<>();
        for(Product lst : lstProduct) {

            lstPartId.add(lst.getPartner().getName());
        }
        for(String lst1 :lstPartId ){
            System.out.println("QQQQQQQQQQQQ"+lst1);
        }
        return lstPartId;*/


}

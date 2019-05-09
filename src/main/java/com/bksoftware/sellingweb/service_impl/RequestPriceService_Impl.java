package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.RequestPrice;
import com.bksoftware.sellingweb.repository.RequestPriceRepository;
import com.bksoftware.sellingweb.service.RequestPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RequestPriceService_Impl implements RequestPriceService {

    private final static Logger LOGGER = Logger.getLogger(RequestPriceService_Impl.class.getName());

    @Autowired
    private RequestPriceRepository requestPriceRepository;


    @Override
    public Page<RequestPrice> findByStatus(Pageable pageable) {
        try {
            return requestPriceRepository.findByStatus(pageable, true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-request-price-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<RequestPrice> findByStatusPage() {
        try {
            return requestPriceRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-request-price-page-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public RequestPrice findById(int id) {
        try {
            RequestPrice requestPrice = requestPriceRepository.findById(id);
            if (requestPrice.isStatus()) return requestPrice;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-request-price-by-id-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveRequestPrice(RequestPrice requestPrice) {
        try {
            requestPriceRepository.save(requestPrice);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-request-price-error {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteRequestPrice(int id) {
        try {
            RequestPrice requestPrice = findById(id);
            requestPrice.setStatus(false);
            requestPriceRepository.save(requestPrice);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-request-price-error {0}", ex.getMessage());
        }
        return false;
    }
}

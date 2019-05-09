package com.bksoftware.sellingweb.service_impl.homepage;

import com.bksoftware.sellingweb.entities.homepage.HomeImage;
import com.bksoftware.sellingweb.repository.homepage.HomeImageRepository;
import com.bksoftware.sellingweb.service.homepage.HomeImageService;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HomeImageService_Impl implements HomeImageService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    private final HomeImageRepository homeImageRepository;

    public HomeImageService_Impl(HomeImageRepository homeImageRepository) {
        this.homeImageRepository = homeImageRepository;
    }

    @Override
    public boolean saveHomeImage(HomeImage homeImage) {
        try {
            homeImageRepository.save(homeImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-home-image-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteHomeImage(HomeImage homeImage) {
        try {
            homeImage.setStatus(false);
            homeImageRepository.save(homeImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-home-image-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<HomeImage> getHomeImage() {

        try {

            return homeImageRepository.findByStatus(true);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-home-image-error : {0}", ex.getMessage());

        }
        return null;
    }
}

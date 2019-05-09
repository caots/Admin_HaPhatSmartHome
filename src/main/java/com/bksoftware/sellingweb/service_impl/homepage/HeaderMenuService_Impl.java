package com.bksoftware.sellingweb.service_impl.homepage;

import com.bksoftware.sellingweb.entities.homepage.HeaderMenu;
import com.bksoftware.sellingweb.repository.homepage.HeaderMenuRepository;
import com.bksoftware.sellingweb.service.homepage.HeaderMenuService;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HeaderMenuService_Impl implements HeaderMenuService {

    private final HeaderMenuRepository headerMenuRepository;

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());


    @Override
    public List<HeaderMenu> findAllHeaderMenu() {

        try {
            return headerMenuRepository.getHeaderMenu();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-header-menu-error : {0}", ex.getMessage());

        }
        return null;
    }

    public HeaderMenuService_Impl(HeaderMenuRepository headerMenuRepository) {
        this.headerMenuRepository = headerMenuRepository;
    }

    @Override
    public boolean saveHeaderMenu(HeaderMenu headerMenu) {
        try {
            headerMenuRepository.save(headerMenu);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-header-menu-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteHeaderMenu(HeaderMenu headerMenu) {
        try {
            headerMenu.setStatus(false);
            headerMenuRepository.save(headerMenu);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-header-menu-error : {0}", ex.getMessage());
            return false;
        }
    }
}

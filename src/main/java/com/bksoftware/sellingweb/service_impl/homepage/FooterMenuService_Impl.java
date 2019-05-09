package com.bksoftware.sellingweb.service_impl.homepage;

import com.bksoftware.sellingweb.entities.homepage.FooterMenu;
import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import com.bksoftware.sellingweb.repository.homepage.FooterMenuDetailsRepository;
import com.bksoftware.sellingweb.repository.homepage.FooterMenuRepository;
import com.bksoftware.sellingweb.service.homepage.FooterMenuService;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FooterMenuService_Impl implements FooterMenuService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    private final FooterMenuRepository footerMenuRepository;
    private final FooterMenuDetailsRepository footerMenuDetailsRepository;

    public FooterMenuService_Impl(FooterMenuRepository footerMenuRepository, FooterMenuDetailsRepository footerMenuDetailsRepository) {
        this.footerMenuRepository = footerMenuRepository;
        this.footerMenuDetailsRepository = footerMenuDetailsRepository;
    }

    public List<FooterMenuDetails> findAllFooterSmall(Pageable pageable) {
        try {
            return footerMenuDetailsRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-footer-small-error: {0}", ex.getMessage());
            return null;
        }
    }

    @Override
    public FooterMenu findById(int id) {
        try {
            FooterMenu footerMenu = footerMenuRepository.findById(id);
            if (footerMenu.isStatus() == true) return footerMenu;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error: {0}", ex.getMessage());
            return null;
        }
    }

    @Override
    public FooterMenuDetails findFooterMenuDetailsById(int id) {
        FooterMenuDetails footerMenuDetails = footerMenuDetailsRepository.findById(id);
        if (footerMenuDetails.isStatus()) return footerMenuDetails;
        return null;
    }

    @Override
    public boolean saveFooterMenu(FooterMenu footerMenu) {
        try {
            footerMenuRepository.save(footerMenu);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-footer-menu-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteFooterMenu(FooterMenu footerMenu) {
        try {
            footerMenu.setStatus(false);
            footerMenuRepository.save(footerMenu);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-footer-menu-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveFooterMenuDetails(FooterMenuDetails footerMenuDetails) {
        try {
            footerMenuDetailsRepository.save(footerMenuDetails);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-footer-menu-details-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteFooterMenuDetails(FooterMenuDetails footerMenuDetails) {
        try {
            footerMenuDetails.setStatus(false);
            footerMenuDetailsRepository.save(footerMenuDetails);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-footer-menu-details-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<FooterMenu> showFooterBig() {
        try {
            return footerMenuRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-footer-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<FooterMenuDetails> showFooterDetails(int idFooterBig) {
        try {
            return footerMenuDetailsRepository.showDetailsById(idFooterBig);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "show-footer-menu-details-error : {0}", ex.getMessage());
        }
        return null;
    }
}

package com.bksoftware.sellingweb.service.homepage;

import com.bksoftware.sellingweb.entities.homepage.FooterMenu;
import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FooterMenuService {

    FooterMenu findById(int id);

    FooterMenuDetails findFooterMenuDetailsById(int id);

    boolean saveFooterMenu(FooterMenu footerMenu);

    boolean deleteFooterMenu(FooterMenu footerMenu);

    boolean saveFooterMenuDetails(FooterMenuDetails footerMenuDetails);

    boolean deleteFooterMenuDetails(FooterMenuDetails footerMenuDetails);
    List<FooterMenu> showFooterBig();

    List<FooterMenuDetails> showFooterDetails(int idFooterBig);
}

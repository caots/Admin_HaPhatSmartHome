package com.bksoftware.sellingweb.service.homepage;

import com.bksoftware.sellingweb.entities.homepage.HeaderMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HeaderMenuService {

    boolean saveHeaderMenu(HeaderMenu headerMenu);

    boolean deleteHeaderMenu(HeaderMenu headerMenu);

    List<HeaderMenu> findAllHeaderMenu();
}

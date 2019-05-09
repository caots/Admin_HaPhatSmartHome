package com.bksoftware.sellingweb.repository.homepage;

import com.bksoftware.sellingweb.entities.homepage.FooterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FooterMenuRepository extends JpaRepository<FooterMenu,Integer> {
    FooterMenu findById(int id);
}

package com.bksoftware.sellingweb.repository.homepage;

import com.bksoftware.sellingweb.entities.homepage.HeaderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeaderMenuRepository extends JpaRepository<HeaderMenu,Integer> {

    @Query("select hm from HeaderMenu hm where hm.status = true")
    public List<HeaderMenu> getHeaderMenu();
}

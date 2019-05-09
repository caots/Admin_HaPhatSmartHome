package com.bksoftware.sellingweb.repository.homepage;

import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FooterMenuDetailsRepository extends JpaRepository<FooterMenuDetails, Integer> {
    @Query("select f from FooterMenuDetails f where f.status=true and f.footerMenu.id= :idFooterBig")
    public List<FooterMenuDetails> showDetailsById(@Param("idFooterBig") int idFooterBig);

    Page<FooterMenuDetails> findByStatus(boolean status, Pageable pageable);

    FooterMenuDetails findById(int id);
}

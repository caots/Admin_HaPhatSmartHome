package com.bksoftware.sellingweb.repository.company;

import com.bksoftware.sellingweb.entities.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

        Company findById(int id);
}

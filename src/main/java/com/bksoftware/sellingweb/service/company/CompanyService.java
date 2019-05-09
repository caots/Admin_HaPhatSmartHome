package com.bksoftware.sellingweb.service.company;



import com.bksoftware.sellingweb.entities.company.Company;


import java.util.List;

public interface CompanyService {

    List<Company> findAllCompanies();

    Company findById(int id);

    boolean saveCompany(Company company);

    boolean deleteCompany(Company company);

}

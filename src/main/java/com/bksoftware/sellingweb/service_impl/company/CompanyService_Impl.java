package com.bksoftware.sellingweb.service_impl.company;

import com.bksoftware.sellingweb.entities.company.Company;
import com.bksoftware.sellingweb.repository.company.CompanyRepository;
import com.bksoftware.sellingweb.service.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CompanyService_Impl implements CompanyService {


    private static final Logger LOGGER = Logger.getLogger(CompanyService_Impl.class.getName());

    private final
    CompanyRepository companyRepository;

    public CompanyService_Impl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        try {
            List<Company> companies = companyRepository.findAll();
            return companies
                    .stream()
                    .filter(p -> p.isStatus() == true)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-company-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Company findById(int id) {
        try {
            Company company = companyRepository.findById(id);
            if (company.isStatus() == true) return company;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-company-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveCompany(Company company) {
        try {
            companyRepository.save(company);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-company-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Company company) {
        try {
            company.setStatus(false);
            companyRepository.save(company);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-company-error : {0}", ex.getMessage());
        }
        return false;
    }
}

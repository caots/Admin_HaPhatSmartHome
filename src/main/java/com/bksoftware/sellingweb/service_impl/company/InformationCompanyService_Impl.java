package com.bksoftware.sellingweb.service_impl.company;

import com.bksoftware.sellingweb.entities.company.InformationCompany;
import com.bksoftware.sellingweb.repository.company.InformationCompanyRepository;
import com.bksoftware.sellingweb.service.company.InformationCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class InformationCompanyService_Impl implements InformationCompanyService {


    private static Logger LOGGER = Logger.getLogger(InformationCompanyService_Impl.class.getName());

    private final
    InformationCompanyRepository informationCompanyRepository;

    public InformationCompanyService_Impl(InformationCompanyRepository informationCompanyRepository) {
        this.informationCompanyRepository = informationCompanyRepository;
    }

    @Override
    public List<InformationCompany> findAllCompanyInformation() {
        try {
            List<InformationCompany> informationCompanies = informationCompanyRepository.findAll();
            return informationCompanies
                    .stream()
                    .filter(p -> p.isStatus() == true)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-information-company-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveInformationCompany(InformationCompany informationCompany) {
        try {
            informationCompanyRepository.save(informationCompany);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-information-company-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteInformationCompany(InformationCompany informationCompany) {
        try {
            informationCompany.setStatus(false);
            informationCompanyRepository.save(informationCompany);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-information-company-error : {0}", ex.getMessage());
        }
        return false;
    }
}

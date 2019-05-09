package com.bksoftware.sellingweb.service.company;


import com.bksoftware.sellingweb.entities.company.InformationCompany;

import java.util.List;

public interface InformationCompanyService {

    List<InformationCompany> findAllCompanyInformation();

    boolean saveInformationCompany(InformationCompany informationCompany);

    boolean deleteInformationCompany(InformationCompany informationCompany);
}

package com.bksoftware.sellingweb.controller.company;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.company.InformationCompany;
import com.bksoftware.sellingweb.service_impl.company.InformationCompanyService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/information-company")
public class InformationCompanyController {

    @Autowired
    InformationCompanyService_Impl informationCompanyService;

    @GetMapping
    public ResponseEntity<List<InformationCompany>> findAllInformationCompany(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<InformationCompany> informationCompanies = informationCompanyService.findAllCompanyInformation();
            if (informationCompanies != null) {
                return new ResponseEntity<>(informationCompanies, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

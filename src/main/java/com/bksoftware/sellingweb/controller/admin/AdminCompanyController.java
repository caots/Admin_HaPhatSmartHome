package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.company.Company;
import com.bksoftware.sellingweb.entities.company.InformationCompany;
import com.bksoftware.sellingweb.service_impl.company.CompanyService_Impl;
import com.bksoftware.sellingweb.service_impl.company.InformationCompanyService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin")
@RolesAllowed("ADMIN")
public class AdminCompanyController {

    private final CompanyService_Impl companyService;
    private final InformationCompanyService_Impl informationCompanyService;

    public AdminCompanyController(CompanyService_Impl companyService, InformationCompanyService_Impl informationCompanyService) {
        this.companyService = companyService;
        this.informationCompanyService = informationCompanyService;
    }

    //-------------------------------COMPANY-----------------------------------
    //add
    @PostMapping("/company")
    public ResponseEntity<Object> addCompany(@RequestBody Company company) {
        company.setStatus(true);
        if (companyService.saveCompany(company)) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else return new ResponseEntity<>("save error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/company")
    public ResponseEntity<Object> updateCompany(@RequestBody Company company) {
        if (companyService.saveCompany(company)) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else return new ResponseEntity<>("update error", HttpStatus.BAD_REQUEST);
    }

    //delete
    @PutMapping("/delete-company")
    public ResponseEntity<String> deleteCompany(@RequestParam("id") int id) {
        Company company = companyService.findById(id);
        System.out.println(company);
        if (companyService.deleteCompany(company)) {
            System.out.println(company);
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
    }

    //-----------------------------Information Company---------------------------
    //add
    @PostMapping(value = "/information-company", params = "company-id")
    public ResponseEntity<String> addInformationCompany(@RequestBody InformationCompany informationCompany,
                                                        @RequestParam(name = "company-id") int companyId) {
        informationCompany.setStatus(true);
        informationCompany.setCompany(companyService.findById(companyId));
        if (informationCompanyService.saveInformationCompany(informationCompany)) {
            return new ResponseEntity<>("save success", HttpStatus.OK);
        } else return new ResponseEntity<>("save error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/information-company")
    public ResponseEntity<String> updateInformationCompany(@RequestBody InformationCompany informationCompany) {
        if (informationCompanyService.saveInformationCompany(informationCompany)) {
            return new ResponseEntity<>("update success", HttpStatus.OK);
        } else return new ResponseEntity<>("update error", HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/delete-information-company")
    public ResponseEntity<String> deleteInformationCompany(@RequestBody InformationCompany informationCompany) {
        if (informationCompanyService.deleteInformationCompany(informationCompany)) {
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
    }
}

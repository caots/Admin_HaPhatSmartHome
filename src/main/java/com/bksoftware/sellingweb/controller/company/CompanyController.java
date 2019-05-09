
package com.bksoftware.sellingweb.controller.company;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.company.Company;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.company.CompanyService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/companies")
public class CompanyController {

    @Autowired
    CompanyService_Impl companyService;

    @Autowired
    CategoryService_Impl categoryService;

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies(
            HttpServletResponse response
            ,@RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<Company> companies = companyService.findAllCompanies();

            if (companies == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("find-by-id")
    public ResponseEntity<Company> finCompanyById(
            HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Company company = companyService.findById(id);

            if (company == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Part;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/public/partner")
public class PartnerController {

    @Autowired
    private PartnerService_Impl partnerService;
    @Autowired
    private CategoryService_Impl categoryService;
    @Autowired
    private ProductService_Impl productService;

    @Autowired
    private RecordService_Impl recordService;

    @GetMapping(value = "/find-by-big-category")
    public ResponseEntity<HashSet<Partner>> findByBigCategory(
            @RequestParam(name = "big-category-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            HashSet<Partner> partners = new HashSet<>();
            List<Product> products = productService.findAllProductByBigCategory(categoryService.findBigCategoryById(id));
            products.forEach(product -> partners.add(product.getPartner()));
            return new ResponseEntity<>(partners, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Partner>> showPartner(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Partner> lstPartner = partnerService.findAllPartner(pageable).getContent();
            return new ResponseEntity<>(lstPartner, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<Partner> findPartnerById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            Partner partner = partnerService.findById(id);
            return new ResponseEntity<>(partner, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Partner>> allPartner(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("partner");
            List<Partner> lstPartner = partnerService.findAllPartnerPage();
            record.setNumber(lstPartner.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(lstPartner, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-all/size")
    public ResponseEntity<Double> findPagePartner(@RequestHeader("adminbksoftwarevn") String header) {
        Record record = recordService.findByName("partner");
        return new ResponseEntity<>(Math.ceil((double) record.getNumber() / 10), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-medium-category")
    public ResponseEntity<HashSet<Partner>> findByMediumCategory(
            @RequestParam("id-medium-category") int id,
            @RequestHeader("adminbksoftwarevn") String header) {
        HashSet<Partner> partners = new HashSet<>();
        if (header.equals(Token.tokenHeader)) {
            List<Product> products = productService.findProductByMedium(id);
            for (Product p : products) {
                partners.add(p.getPartner());
            }
            return new ResponseEntity<>(partners, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-small-category")
    public ResponseEntity<HashSet<Partner>> findBySmallCategory(
            @RequestParam("id-small-category") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            HashSet<Partner> partners = new HashSet<>();
            List<Product> products = productService.findProductBySmall(id);
            for (Product p : products) {
                partners.add(p.getPartner());
            }
            return new ResponseEntity<>(partners, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

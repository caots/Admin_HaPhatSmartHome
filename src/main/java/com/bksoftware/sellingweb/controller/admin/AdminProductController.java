package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/v1/admin")
public class AdminProductController {
    private final ProductService_Impl productService;
    private final CategoryService_Impl categoryService;
    private final PartnerService_Impl partnerService;

    public AdminProductController(
            ProductService_Impl productService,
            CategoryService_Impl categoryService,
            PartnerService_Impl partnerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.partnerService = partnerService;
    }

    @Autowired
    private RecordService_Impl recordService;

    // ---------------------- PARTNER---------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/partner")
    public ResponseEntity<Object> addPartner(@RequestBody Partner partner) {
        Record record = recordService.findByName("partner");
        partner.setStatus(true);
        if (partnerService.savePartner(partner)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(partner, HttpStatus.OK);
        } else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/partner")
    public ResponseEntity<Object> updatePartner(@RequestBody Partner partner) {
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>(partner, HttpStatus.OK);
        else
            return new ResponseEntity<>("update fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-partner")
    public ResponseEntity<String> deletePartner(@RequestParam("id") int id) {
        Record record = recordService.findByName("partner");
        Partner partner = partnerService.findById(id);
        if (partnerService.deletePartner(partner)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
    }


    // -------------------------PRODUCT------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/product", params = {"small-category-id", "partner-id"})
    public ResponseEntity<Object> addProduct(@RequestBody Product product,
                                             @RequestParam(name = "small-category-id") int smallCategoryId,
                                             @RequestParam(name = "partner-id") int partnerId) {
        Record record = recordService.findByName("product");
        product.setId(productService.findAll().size() + 2);
        product.setStatus(true);
        product.setView(0);
        product.setInitDate(LocalDateTime.now());
        product.setSmallCategory(categoryService.findSmallCategoryById(smallCategoryId));
        product.setPartner(partnerService.findById(partnerId));
        if (productService.saveProduct(product)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else return new ResponseEntity<>("add product fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        if (productService.saveProduct(product))
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>("update product fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") int idProduct) {
        Product product = productService.findById(idProduct);
        Record record = recordService.findByName("product");
        if (productService.deleteProduct(product)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete product success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete product fail", HttpStatus.BAD_REQUEST);
    }

}

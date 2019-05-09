package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.product.Feature;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.entities.product.ProductImage;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductDetailsService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/product-details")
public class AdminProductDetailsController {

    private final ProductDetailsService_Impl productDetailsService;
    private final ProductService_Impl productService;

    public AdminProductDetailsController(ProductDetailsService_Impl productDetailsService, ProductService_Impl productService) {
        this.productDetailsService = productDetailsService;
        this.productService = productService;
    }

    @Autowired
    private RecordService_Impl recordService;

    //---------------------PRODUCT DETAILS----------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(params = "product-id")
    public ResponseEntity<Object> addProductDetails(@RequestBody ProductDetails productDetails,
                                                    @RequestParam(value = "product-id") int id) {

        Record record = recordService.findByName("details-product");
        productDetails.setId(productDetailsService.findAll().size() + 2);
        productDetails.setProductStatus(true);
        productDetails.setStatus(true);
        productDetails.setProduct(productService.findById(id));
        if (productDetailsService.saveProductDetails(productDetails)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        } else return new ResponseEntity<>("add productDetails fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping
    public ResponseEntity<Object> updateProductDetails(@RequestBody ProductDetails productDetails) {
        if (productDetailsService.saveProductDetails(productDetails))
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        else return new ResponseEntity<>("update productDetails fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-details-product")
    public ResponseEntity<String> deleteDetailsProduct(@RequestParam("id") int id) {
        ProductDetails productDetails = productDetailsService.findById(id);
        Record record = recordService.findByName("details-product");
        if (productDetailsService.saveProductDetails(productDetails)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete feature success", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete feature fail", HttpStatus.BAD_REQUEST);
    }


    //---------------------FEATURE-------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/feature", params = "product-details-id")
    public ResponseEntity<String> addFeature(@RequestBody Feature feature,
                                             @RequestParam(value = "product-details-id") int id) {
        ProductDetails productDetails = productDetailsService.findById(id);
        feature.setProductDetails(productDetails);
        feature.setStatus(true);
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("add feature success", HttpStatus.OK);
        return new ResponseEntity<>("add feature fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/feature")
    public ResponseEntity<String> updateFeature(@RequestBody Feature feature) {
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("update feature success", HttpStatus.OK);
        return new ResponseEntity<>("update feature fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-feature")
    public ResponseEntity<String> deleteFeature(@RequestBody Feature feature) {
        if (productDetailsService.deleteFeature(feature))
            return new ResponseEntity<>("delete feature success", HttpStatus.OK);
        return new ResponseEntity<>("delete feature fail", HttpStatus.BAD_REQUEST);
    }

    //---------------------------productImage--------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/product-image", params = "product-details-id")
    public ResponseEntity<Object> addProductImage(@RequestBody ProductImage productImage,
                                                  @RequestParam(value = "product-details-id") int id) {
        ProductDetails productDetails = productDetailsService.findById(id);
        productImage.setProductDetails(productDetails);
        productImage.setStatus(true);
        if (productDetailsService.saveProductImage(productImage))
            return new ResponseEntity<>(productImage, HttpStatus.OK);
        return new ResponseEntity<>("add product image fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/product-image")
    public ResponseEntity<String> updateProductImage(@RequestBody ProductImage productImage) {
        if (productDetailsService.saveProductImage(productImage))
            return new ResponseEntity<>("update product image success", HttpStatus.OK);
        return new ResponseEntity<>("update product image fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-product-image")
    public ResponseEntity<String> deleteProductImage(@RequestBody ProductImage productImage) {
        if (productDetailsService.deleteProductImage(productImage))
            return new ResponseEntity<>("delete product image success", HttpStatus.OK);
        return new ResponseEntity<>("delete product image fail", HttpStatus.BAD_REQUEST);
    }
}

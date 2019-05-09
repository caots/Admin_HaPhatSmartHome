package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.product.*;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductDetailsService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "api/v1/public/products")
public class ProductController {

    @Autowired
    ProductService_Impl productService;

    @Autowired
    ProductDetailsService_Impl productDetailsService_imp;

    @Autowired
    PartnerService_Impl partnerService_imp;

    @Autowired
    RecordService_Impl recordService;

    private static String productNameSearch = "";

    private static String detailsProductNameSearch = "";

    private List<ProductAndDetails> productAndDetails(List<Product> products) {

        List<ProductAndDetails> listProductAndDetails = new ArrayList<>();
        products.forEach(p -> {
            ProductDetails productDetails = productDetailsService_imp.findByProduct(p);
            ProductAndDetails productAndDetails = new ProductAndDetails();
            productAndDetails.setId(p.getId());
            productAndDetails.setName(p.getName());
            productAndDetails.setOriginCost(p.getOriginCost());
            productAndDetails.setSaleCost(p.getSaleCost());
            productAndDetails.setProductStatus(productDetails.isProductStatus());
            productAndDetails.setGuarantee(productDetails.getGuarantee());
            productAndDetails.setPresent(productDetails.getPresent());
            listProductAndDetails.add(productAndDetails);
        });

        return listProductAndDetails;
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<ProductAndDetails>> findAllProduct(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {


        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {

            Pageable pageable = PageRequest.of(page - 1, size);

            List<Product> products = productService.findAllProduct(pageable).getContent();

            return new ResponseEntity<>(productAndDetails(products), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/size")
    public ResponseEntity<Double> pageNumberProduct() {
        float pageNumber = recordService.findByName("product").getNumber();
        double result = Math.ceil((double) pageNumber / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> allProduct(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<Product> products = productService.findAllPro();
            Record record = recordService.findByName("product");
            record.setNumber(products.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/details-products")
    public ResponseEntity<List<ProductDetails>> findAllDetailsProduct(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
            Pageable pageable = PageRequest.of(page - 1, size);
            List<ProductDetails> productDetailsList = productDetailsService_imp.findAllProductDetails(pageable).getContent();
            return new ResponseEntity<>(productDetailsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("details-products/size")
    public ResponseEntity<Double> pageNumberDetailsProduct(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            float pageNumber = recordService.findByName("details-product").getNumber();
            double result = Math.ceil((double) pageNumber / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("details-products/all")
    public ResponseEntity<List<ProductDetails>> allDetailsProduct(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<ProductDetails> productDetails = productDetailsService_imp.findAllDePro();
            Record record = recordService.findByName("details-product");
            record.setNumber(productDetails.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<List<ProductAndDetails>> findProductByName(
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            productNameSearch = name;
            Sort sortable = productService.sortData(sort);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> productsByName = productService.findProductByName(name, pageable).getContent();
            productsByName.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productAndDetails(productsByName), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search-home")
    public ResponseEntity<List<Product>> findProductHomeByName(
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            productNameSearch = name;
            Sort sortable = productService.sortData(sort);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> productsByName = productService.findProductByName(name, pageable).getContent();
            productsByName.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productsByName, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/details-product")
    public ResponseEntity<List<ProductDetails>> findDetailsProductByProductName(
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "18") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        detailsProductNameSearch = name;
        Sort sortable = productService.sortData(sort);
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<ProductDetails> productDetails = productDetailsService_imp.findDetailsProductByName(name, pageable).getContent();
            productDetails.stream()
                    .filter(p -> (p.isStatus()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/name/size")
    public ResponseEntity<Double> findProductByNamePage(
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Product> productsByName = productService.findProductByNamePage(productNameSearch);
            productsByName.stream()
                    .filter(p -> (p.isStatus()))
                    .collect(Collectors.toList());

            double result = Math.ceil((double) productsByName.size() / size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //@@@@@@@@@
//    @GetMapping("/find-all-by-name")
//    public ResponseEntity<List<Product>> findAllProductByName(@RequestParam("name") String name) {
//        List<Product> productsByName = productService.findProductByNamePage(name);
//        productsByName.stream()
//                .filter(p -> (p.isStatus()))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(productsByName, HttpStatus.OK);
//    }

    @GetMapping("details-product/name/size")
    public ResponseEntity<Double> findDetailsProductByNamePage(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<ProductDetails> productByNamePage = productDetailsService_imp.findDetailsProductByNamePage(productNameSearch);
            productByNamePage.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
            double result = Math.ceil((double) productByNamePage.size() / 10);
            System.out.println(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/featureProductById")
    public ResponseEntity<List<Feature>> showFeatureById(
            @RequestParam("idProduct") int idProduct,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<Feature> lstFeature = productDetailsService_imp.showFeatureById(idProduct);
            return new ResponseEntity<>(lstFeature, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bySmallCategory")
    public ResponseEntity<List<Product>> showProduct(
            HttpServletResponse response,
            @RequestParam(name = "id") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortDataProduct(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            Page<Product> lstProduct = productService.showProductByBigCategory(id, pageable);
            return new ResponseEntity<>(lstProduct.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/bySmallCategory/size")
    public ResponseEntity<Double> showProductBySmallCategoryPage(
            HttpServletResponse response,
            @RequestParam(name = "id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {


        if (header.equals(Token.tokenHeader)) {
            List<Product> lstProduct = productService.showProductBySmallCategoryPage(id);
            double result = Math.ceil((double) lstProduct.size() / 18);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byMediumCategory")
    public ResponseEntity<List<Product>> showProductMedium(
            HttpServletResponse response,
            @RequestParam(name = "id") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortDataProduct(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            Page<Product> lstProductMedium = productService.showProductByMedium(id, pageable);
            return new ResponseEntity<>(lstProductMedium.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byMediumCategory/size")
    public ResponseEntity<Double> showProductMedium(
            HttpServletResponse response,
            @RequestParam(name = "id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {


        if (header.equals(Token.tokenHeader)) {
            List<Product> lstProduct = productService.showProductByMediumCategoryPage(id);
            double result = Math.ceil((double) lstProduct.size() / 18);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byBigCategory")
    public ResponseEntity<List<Product>> showProductByBig(
            HttpServletResponse response,
            @RequestParam(name = "id") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortDataProduct(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            Page<Product> lstProductBig = productService.showProductByBig(id, pageable);
            return new ResponseEntity<>(lstProductBig.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byBigCategory/size")
    public ResponseEntity<Double> showProductByBig(
            HttpServletResponse response,
            @RequestParam(name = "id") int id) {

        List<Product> lstProduct = productService.showProductByBigCategoryPage(id);
        double result = Math.ceil((double) lstProduct.size() / 18);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/big-branch")
    public ResponseEntity<List<Product>> showProductByBigBranch(
            HttpServletResponse response,
            @RequestParam(name = "id-big") int id,
            @RequestParam(name = "id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
//            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
//            @RequestParam(name = "size", required = false, defaultValue = "100") Integer size,
//            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
//           @RequestParam(name = "field", required = false, defaultValue = "name") String field
    ) {
        if (header.equals(Token.tokenHeader)) {

            List<Product> lstProductBig = productService.showProductByBigBranch(id, branch);
            return new ResponseEntity<>(lstProductBig, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/small-branch")
    public ResponseEntity<List<Product>> showProductBySmallBranch(
            HttpServletResponse response,
            @RequestParam(name = "id-small") int id,
            @RequestParam(name = "id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
//            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
//            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
//            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
//            @RequestParam(name = "field", required = false, defaultValue = "name") String field
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<Product> lstProductSmall = productService.showProductBySmallBranch(id, branch);
            return new ResponseEntity<>(lstProductSmall, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byMediumCategoryBranch")
    public ResponseEntity<List<Product>> showProductByMediumBranch(
            HttpServletResponse response,
            @RequestParam(name = "id-medium") int id,
            @RequestParam(name = "id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
//            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
//            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
//            @RequestParam(name = "field", required = false, defaultValue = "name") String field
    ) {
        if (header.equals(Token.tokenHeader)) {

            List<Product> lstProductMedium = productService.showProductByMediumBranch(id, branch);
            return new ResponseEntity<>(lstProductMedium, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findProductByPrice")
    public ResponseEntity<List<Product>> findProductByPrice(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "low") int low,
            @RequestParam(name = "high") int high,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortDataProduct(type, field);
            Pageable pageable = PageRequest.of(page, size, sortable);
            Page<Product> lstProductPrice = productService.findProductByPrice(id, low, high, pageable);
            return new ResponseEntity<>(lstProductPrice.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findProductByPriceBranch")
    public ResponseEntity<List<Product>> findProductByPriceBranch(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "low") int low,
            @RequestParam(name = "high") int high,
            @RequestParam(name = "branch") int branch,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortDataProduct(type, field);
            Pageable pageable = PageRequest.of(page, size, sortable);
            Page<Product> lstProductPrice = productService.findProductByPriceBranch(id, low, high, branch, pageable);
            return new ResponseEntity<>(lstProductPrice.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/showProductSale")
    public ResponseEntity<List<Product>> showProductSale(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field
            , @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortDataProduct(type, field);
            Pageable pageable = PageRequest.of(page, size, sortable);
            Page<Product> lstProductSale = productService.showProductSale(pageable);
            return new ResponseEntity<>(lstProductSale.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/productDetails")
    public ResponseEntity<ProductDetails> showProductDetails(
            @RequestParam("idProduct") int idProduct,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            ProductDetails productDetails = productDetailsService_imp.showProductDetails(idProduct);
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/findProductById")
    public ResponseEntity<Product> findProductById(
            @RequestParam("id") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Product product = productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-details-product-by-id")
    public ResponseEntity<ProductDetails> findDetailsProductById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            ProductDetails productDetails = productDetailsService_imp.findById(id);
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/new-products")
    public ResponseEntity<List<Product>> newProducts(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<Product> products = productService.findNewProducts(pageable);
            if (products == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(products.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/new-products/size")
    public ResponseEntity<Double> newProductsPage(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<Product> newProducts = productService.findNewProductsPage();
            double result = Math.ceil(newProducts.size() / 18);
            if ((newProducts.size() / 18) % 2 == 0 && (newProducts.size() / 18) != 0) result -= 1;
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/hot-products")
    public ResponseEntity<List<Product>> hotProduct(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Product> products = productService.findHotProducts(pageable).getContent();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/hot-products/size")
    public ResponseEntity<Double> hotProductPage(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<Product> hotProduct = productService.findHotProductsPage();
            double result = Math.ceil((double) hotProduct.size() / 18);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/big-price")
    public ResponseEntity<List<Product>> findProductByBigCategoryAboutPrice(
            HttpServletResponse response,
            @RequestParam("id-big") int idBigCategory,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductByBigCategoryAboutPrice(idBigCategory, startPrice, endPrice), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //*
    @GetMapping("/big-price-branch")
    public ResponseEntity<List<Product>> findProductByBigCategoryAboutPriceAndBranch(
            HttpServletResponse response,
            @RequestParam("id-big") int idBigCategory,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestParam("id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductByBigCategoryAboutPriceAndBranch(idBigCategory, startPrice, endPrice, branch), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/medium-price")
    public ResponseEntity<List<Product>> findProductByMediumCategoryAboutPrice(
            HttpServletResponse response,
            @RequestParam("id-medium") int idMediumCategory,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductByMediumCategoryAboutPrice(idMediumCategory, startPrice, endPrice), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //*
    @GetMapping("/medium-price-branch")
    public ResponseEntity<List<Product>> findProductByMediumCategoryAboutPriceAndBranch(
            HttpServletResponse response,
            @RequestParam("id-medium") int idMediumCategory,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestParam("id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductByMediumCategoryAboutPriceAndBranch(idMediumCategory, startPrice, endPrice, branch), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/small-price")
    public ResponseEntity<List<Product>> findProductBySmallCategoryAboutPrice(
            HttpServletResponse response,
            @RequestParam("id-small") int idSmallCategory,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductBySmallCategoryAboutPrice(idSmallCategory, startPrice, endPrice), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //*
    @GetMapping("/small-price-branch")
    public ResponseEntity<List<Product>> findProductBySmallCategoryAboutPriceAndBranch(
            HttpServletResponse response,
            @RequestParam("id-small") int idSmallCategory,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestParam("id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductBySmallCategoryAboutPriceAndBranch(idSmallCategory, startPrice, endPrice, branch), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/hot-price")
    public ResponseEntity<List<Product>> findProductByHotAboutPrice(
            HttpServletResponse response,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(productService.findProductByHotAboutPrice(startPrice, endPrice), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //*
    @GetMapping("/hot-price-branch")
    public ResponseEntity<List<Product>> findProductByHotAboutPrice(
            HttpServletResponse response,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestParam("id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductByHotAboutPriceAndBranch(startPrice, endPrice, branch), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/new-price")
    public ResponseEntity<List<Product>> findProductByNewAboutPrice(
            HttpServletResponse response,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productService.findProductByNewAboutPrice(startPrice, endPrice), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //*
    @GetMapping("/new-price-branch")
    public ResponseEntity<List<Product>> findProductByNewAboutPriceAndBranch(
            HttpServletResponse response,
            @RequestParam("start-price") double startPrice,
            @RequestParam("end-price") double endPrice,
            @RequestParam("id-branch") int branch,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(productService.findProductByNewAboutPriceAndBranch(startPrice, endPrice, branch), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



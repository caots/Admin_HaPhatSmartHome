package com.bksoftware.sellingweb.controller.category;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/category")
public class CategoryController {

    @Autowired
    CategoryService_Impl categoryService_imp;

    @Autowired
    private RecordService_Impl recordService;

    @GetMapping(value = "/showBig")
    public ResponseEntity<List<BigCategory>> showBigCategory(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;

            Pageable pageable = PageRequest.of(page - 1, size);
            List<BigCategory> lstBigCategory = categoryService_imp.showBigCategory(pageable).getContent();
            return new ResponseEntity<>(lstBigCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/showBig/size")
    public ResponseEntity<Double> pageNumberBigCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header

    ) {


        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("big-category");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/showBig/all")
    public ResponseEntity<List<BigCategory>> allBigCategory(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("big-category");

            List<BigCategory> bigCategories = categoryService_imp.findAllBigCategoryPage();
            record.setNumber(bigCategories.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(bigCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/medium-category")
    public ResponseEntity<List<MediumCategory>> showMediumCategory(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size);
            List<MediumCategory> mediumCategories = categoryService_imp.findAllMediumCategory(pageable).getContent();
            return new ResponseEntity<>(mediumCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/medium-category/size")
    public ResponseEntity<Double> pageNumberMediumCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        Record record = recordService.findByName("medium-category");
        if (header.equals(Token.tokenHeader)) {

            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/medium-category/all")
    public ResponseEntity<List<MediumCategory>> allMediumCategory(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("medium-category");
            List<MediumCategory> lstMediumCategory = categoryService_imp.findAllMediumCategoryPage();
            record.setNumber(lstMediumCategory.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(lstMediumCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/small-category")
    public ResponseEntity<List<SmallCategory>> showSmallCategory(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;

        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<SmallCategory> lstSmallCategory = categoryService_imp.findAllSmallCategory(pageable).getContent();
            return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/small-category/size")
    public ResponseEntity<Double> pagesNumberSmallCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("small-category");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/small-category/all")
    public ResponseEntity<List<SmallCategory>> allSmallCategory(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("small-category");
            List<SmallCategory> lstSmallCategory = categoryService_imp.findAllSmallCategoryPage();
            record.setNumber(lstSmallCategory.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/showMedium")
    public ResponseEntity<List<MediumCategory>> showMediumCategory(
            @RequestParam("idBigCategory") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<MediumCategory> lstMediumCategory = categoryService_imp.showMediumCategory(id);
            return new ResponseEntity<>(lstMediumCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/showSmall")
    public ResponseEntity<List<SmallCategory>> showSmallCategory(
            @RequestParam("idMediumCategory") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<SmallCategory> lstSmallCategory = categoryService_imp.showSmallCategory(id);
            return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findBigCategoryById")
    public ResponseEntity<BigCategory> findBigCategoryById(
            @RequestParam("idBig") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            BigCategory bigCategory = categoryService_imp.findBigCategoryById(id);
            return new ResponseEntity<>(bigCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findMediumCategoryById")
    public ResponseEntity<MediumCategory> findMediumCategoryById(
            @RequestParam("idMedium") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            MediumCategory mediumCategory = categoryService_imp.findMediumCategoryById(id);
            return new ResponseEntity<>(mediumCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findSmallCategoryById")
    public ResponseEntity<SmallCategory> findSmallCategoryById(
            @RequestParam("idSmall") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            SmallCategory smallCategory = categoryService_imp.findSmallCategoryById(id);
            return new ResponseEntity<>(smallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

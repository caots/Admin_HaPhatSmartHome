package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/category")
public class AdminCategoryController {
    private final CategoryService_Impl categoryService;

    public AdminCategoryController(CategoryService_Impl categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    private RecordService_Impl recordService;
    // -----------------------------------Category-----------------------------------------


    //************************************************************************add

    //    @RolesAllowed("ADMIN")
    @PostMapping(value = "/big")
    public ResponseEntity<Object> addBigCategory(@RequestBody BigCategory bigCategory) {
        Record record = recordService.findByName("big-category");
        bigCategory.setStatus(true);
        if (categoryService.saveBigCategory(bigCategory)) {
            record.setNumber(record.getNumber()+1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(bigCategory, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("add fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/medium", params = "big-id")
    public ResponseEntity<Object> addMediumCategory(@RequestBody MediumCategory mediumCategory,
                                                    @RequestParam(value = "big-id") int id) {
        Record record = recordService.findByName("medium-category");

        BigCategory bigCategory = categoryService.findBigCategoryById(id);
        mediumCategory.setBigCategory(bigCategory);
        mediumCategory.setStatus(true);
        if (categoryService.saveMediumCategory(mediumCategory)) {
            record.setNumber(record.getNumber()+1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(mediumCategory, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);

    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/small", params = "medium-id")
    public ResponseEntity<Object> addMediumCategory(@RequestBody SmallCategory smallCategory,
                                                    @RequestParam(value = "medium-id") int id) {
        Record record = recordService.findByName("small-category");

        MediumCategory mediumCategory = categoryService.findMediumCategoryById(id);
        smallCategory.setMediumCategory(mediumCategory);
        smallCategory.setStatus(true);
        if (categoryService.saveSmallCategory(smallCategory)) {
            record.setNumber(record.getNumber()+1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(smallCategory, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }


    //************************************************************UPDATE


    @RolesAllowed("ADMIN")
    @PutMapping(value = "/big")
    public ResponseEntity<String> updateBigCategory(@RequestBody BigCategory bigCategory) {
        if (categoryService.saveBigCategory(bigCategory))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/medium")
    public ResponseEntity<Object> updateMediumCategory(@RequestBody MediumCategory mediumCategory) {
        if (categoryService.saveMediumCategory(mediumCategory))
            return new ResponseEntity<>(mediumCategory, HttpStatus.OK);
        else
            return new ResponseEntity<>("update fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/small")
    public ResponseEntity<Object> updateSmallCategory(@RequestBody SmallCategory smallCategory) {
        if (categoryService.saveSmallCategory(smallCategory))
            return new ResponseEntity<>(smallCategory, HttpStatus.OK);
        else
            return new ResponseEntity<>("update fails", HttpStatus.BAD_REQUEST);
    }


    //************************************************************delete


    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-big")
    public ResponseEntity<String> deleteBigCategory(@RequestParam("id") int idBigCategory) {
        Record record = recordService.findByName("big-category");

        BigCategory bigCategory = categoryService.findBigCategoryById(idBigCategory);
        if (categoryService.deleteBigCategory(bigCategory)){
            record.setNumber(record.getNumber()-1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete success", HttpStatus.OK);}
        else
            return new ResponseEntity<>("delete fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-medium")
    public ResponseEntity<String> deleteMediumCategory(@RequestParam("id") int idMediumCategory) {
        Record record = recordService.findByName("medium-category");

        MediumCategory mediumCategory = categoryService.findMediumCategoryById(idMediumCategory);
        if (categoryService.deleteMediumCategory(mediumCategory)){
            record.setNumber(record.getNumber()-1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete success", HttpStatus.OK);}
        else
            return new ResponseEntity<>("delete fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-small")
    public ResponseEntity<String> deleteSmallCategory(@RequestParam("id") int idSmallCategory) {
        Record record = recordService.findByName("small-category");

        SmallCategory smallCategory = categoryService.findSmallCategoryById(idSmallCategory);
        if (categoryService.deleteSmallCategory(smallCategory)){
            record.setNumber(record.getNumber()-1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete success", HttpStatus.OK);}
        else
            return new ResponseEntity<>("delete fails", HttpStatus.BAD_REQUEST);
    }

}

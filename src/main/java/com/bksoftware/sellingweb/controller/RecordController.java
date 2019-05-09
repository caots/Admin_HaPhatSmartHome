package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "api/v1/record")
public class RecordController {

}

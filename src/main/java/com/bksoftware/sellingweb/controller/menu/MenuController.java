package com.bksoftware.sellingweb.controller.menu;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.homepage.*;
import com.bksoftware.sellingweb.service_impl.RecordService_Impl;
import com.bksoftware.sellingweb.service_impl.homepage.FooterMenuService_Impl;
import com.bksoftware.sellingweb.service_impl.homepage.HeaderMenuService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/menu")
public class MenuController {
    @Autowired
    private HeaderMenuService_Impl headerMenu_Imp;


    @Autowired
    private FooterMenuService_Impl footerMenuService_imp;

    @Autowired
    private RecordService_Impl recordService;


    @GetMapping(value = "/headerMenu")
    public ResponseEntity<List<HeaderMenu>> showHeaderMenu(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<HeaderMenu> lstMenu = headerMenu_Imp.findAllHeaderMenu();
            return new ResponseEntity<>(lstMenu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/footerBig")
    public ResponseEntity<List<FooterMenu>> showFooterBig(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header) {

        if (header.equals(Token.tokenHeader)) {
            List<FooterMenu> lstFooter = footerMenuService_imp.showFooterBig();
            return new ResponseEntity<>(lstFooter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/footerBig/find-by-id")
    public ResponseEntity<FooterMenu> showFooterBigById(
            HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods","*");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("access-control-allow-credentials" , "true");
        if (header.equals(Token.tokenHeader)) {
            FooterMenu footer = footerMenuService_imp.findById(id);
            return new ResponseEntity<>(footer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/footerSmall")
    public ResponseEntity<List<FooterMenuDetails>> showFooterSmall(
            @RequestParam("idFooterBig") int idFooterBig,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods","*");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("access-control-allow-credentials" , "true");
        if (header.equals(Token.tokenHeader)) {
            List<FooterMenuDetails> lstFooterDetail = footerMenuService_imp.showFooterDetails(idFooterBig);
            return new ResponseEntity<>(lstFooterDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/footerSmall/size")
    public ResponseEntity<Double> showFooterSmallSize(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods","*");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("access-control-allow-credentials" , "true");
        if (header.equals(Token.tokenHeader)) {
            float pageNumber = recordService.findByName("details-footer").getNumber();
            double result = Math.ceil((double) pageNumber / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-all-footer-small")
    public ResponseEntity<List<FooterMenuDetails>> findAllFooterSmall(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods","*");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("access-control-allow-credentials" , "true");
        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
            Pageable pageable = PageRequest.of(page - 1, size);
            List<FooterMenuDetails> lstFooterDetail = footerMenuService_imp.findAllFooterSmall(pageable);
            return new ResponseEntity<>(lstFooterDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/footerSmall/find-by-id")
    public ResponseEntity<FooterMenuDetails> showFooterSmallById(
            @RequestParam("id") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            FooterMenuDetails footerMenuDetails = footerMenuService_imp.findFooterMenuDetailsById(id);
            return new ResponseEntity<>(footerMenuDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

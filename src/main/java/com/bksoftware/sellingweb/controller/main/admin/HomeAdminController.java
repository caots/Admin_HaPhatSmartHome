package com.bksoftware.sellingweb.controller.main.admin;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
////import com.bksoftware.sellingweb.security.SecurityConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeAdminController {

    private String token;

//    public String getToken(HttpServletRequest request) {
//        Cookie cookies[] = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            if (cookies[i].getName().equals("token")) {
//                token = cookies[i].getValue();
//            }
//        }
//
//        String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
//                .build().verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
//                .getSubject();
//
//        return username;
//    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String pageLogin(HttpServletRequest request) {
        Cookie cookies[] = request.getCookies();
        if (cookies!= null) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
            }
        }
        return "login";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "homeAdmin";


    }

    //========================= Product =================================

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String cartPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "cart";
    }


    @RequestMapping(value = {"/send-mail"}, method = RequestMethod.GET)
    public String sendMailPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "sendMail";
    }

    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String productPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "product";
    }

    @GetMapping("/details-product")
    public String detailsProductPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "detailsProduct";
    }


    //========================= Category =================================
    @GetMapping("/big-category")
    public String bigCategoryPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "bigCategory";
    }

    @GetMapping("/medium-category")
    public String mediumCategoryPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "mediumCategory";
    }

    @GetMapping("/small-category")
    public String smallCategoryPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "smallCategory";
    }

    //========================= Partner =================================
    @GetMapping("/partner")
    public String partnerPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "partner";
    }

    //============================== Company ============================
    @GetMapping("/request-price")
    public String RequestPricePage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "RequestPricePage";
    }

    //============================== Company ============================
    @GetMapping("/company")
    public String companyPage(HttpServletRequest request) {
//
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "companyPage";
    }

    //============================== Footer =============================
    @GetMapping("/footer")
    public String footerPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "footerPage";
    }

    @GetMapping("/details-footer")
    public String detailsFooterPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "detailsFooterPage";
    }


}

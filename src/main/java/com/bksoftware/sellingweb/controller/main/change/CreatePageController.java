package com.bksoftware.sellingweb.controller.main.change;


//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.bksoftware.sellingweb.controller.main.admin.HomeAdminController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CreatePageController {

//    public String getToken(HttpServletRequest request) {
//        HomeAdminController homeAdminController = new HomeAdminController();
//        String token = homeAdminController.getToken(request);
//        return token;
//    }

    //=========================Category=================================
    @GetMapping("/create-category")
    public String createCategoryPage(HttpServletRequest request) {


//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createCategory";
    }

    @GetMapping("/update-category")
    public String updateCategoryPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "updateCategory";
    }


    //========================= PRODUCT =================================

    @GetMapping("/create-product")
    public String createProductPage(HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createProduct";
    }

    @GetMapping("/update-product")
    public String updateProductPage(@RequestParam("id") int id,
                                    HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "updateProduct";
    }

    //========================= NEWS PRODUCT - FOOTER  =================================

    @GetMapping("/create-news-product")
    public String createNewsProductPage(HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createNewsProduct";
    }

    @GetMapping("/create-info-product")
    public String createInfoProductPage(HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createInfoProduct";
    }

    @GetMapping("/create-news-footer")
    public String createNewsFooterPage(HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createNewsFooter";
    }

    //========================= PARTNER =================================

    @GetMapping("/create-partner")
    public String createPartnerPage(HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createPartner";
    }

    @GetMapping("/update-partner}")
    public String updatePartnerPage(@RequestParam("id") int id,
                                    HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "updatePartner";
    }

    //============================== Company ==============================
    @GetMapping("/update-company")
    public String updateCompanyPage(@RequestParam("id") int id,
                                    HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "updateCompanyPage";
    }

    @GetMapping("/create-company")
    public String createCompanyPage() {
        return "createCompanyPage";
    }

    //============================== Footer ==============================

    @GetMapping("/update-footer")
    public String updateFooterPage(@RequestParam("id") int id, HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "updateFooterPage";
    }

    @GetMapping("/create-details-footer")
    public String createDetailsFooterPage(HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "createDetailsFooterPage";
    }

    @GetMapping("/update-details-footer")
    public String updateDetailsFooterPage(@RequestParam("id") int id, HttpServletRequest request) {
//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "updateDetailsFooterPage";
    }

}

package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.dao.BuyFormDao;
import com.bksoftware.sellingweb.entities.Cart;
import com.bksoftware.sellingweb.entities.UserMail;
import com.bksoftware.sellingweb.entities.product.*;
import com.bksoftware.sellingweb.service_impl.SendMailService_Impl;
import com.bksoftware.sellingweb.service_impl.company.InformationCompanyService_Impl;
import com.bksoftware.sellingweb.service_impl.product.BuyFormService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/v1/public/buy-form")
public class BuyFormController {

    @Autowired
    private SendMailService_Impl sendMailService;

    @Autowired
    private UserMail user;

    private Set<BuyFormCart> buyFormCarts = new HashSet<>();

    private final BuyFormService_Impl buyFormService;
    private final ProductService_Impl productService;

    public BuyFormController(BuyFormService_Impl buyFormService, ProductService_Impl productService) {
        this.buyFormService = buyFormService;
        this.productService = productService;
    }

    private static Logger LOGGER = Logger.getLogger(BuyFormController.class.getName());


    public Set<BuyFormCart> findAllBuyFormCart(List<BuyFormHasProduct> buyFormHasProducts) {


        buyFormHasProducts.forEach(buyFormHasProduct -> {
            BuyFormCart buyFormCart = new BuyFormCart();
            BuyForm buyForm = buyFormService.findById(buyFormHasProduct.getBuyFormId());
            buyFormCart.setId(buyForm.getId());
            buyFormCart.setName(buyForm.getName());
            buyFormCart.setEmail(buyForm.getEmail());
            buyFormCart.setPhoneNumber(buyForm.getPhoneNumber());
            buyFormCart.setDate(buyForm.getDate());
            buyFormCart.setNote(buyForm.getNote());
            Set<String> nameProduct = new HashSet<>();
            buyForm.getProducts().forEach(product -> {
                nameProduct.add(product.getName());
            });
            buyFormCart.setProducts(nameProduct);
            long total = buyForm.getProducts().stream().mapToLong(bf -> (long) bf.getSaleCost()).sum();
            buyFormCart.setPrice(total);
            buyFormCart.setChecked(false);
            buyFormCart.setQuantity(buyFormHasProducts.stream().mapToInt(bfhp -> bfhp.getQuantity()).sum());
            buyFormCarts.add(buyFormCart);
        });
        return buyFormCarts;
    }

    @GetMapping
    public ResponseEntity<Set<BuyFormCart>> findAllBuyFormHasProduct(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
            Pageable pageable = PageRequest.of(page - 1, size);
            List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProduct(pageable).getContent();
            System.out.println("hihihi: " + buyFormHasProducts.size());
            return new ResponseEntity<>(findAllBuyFormCart(buyFormHasProducts), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("size")
    public ResponseEntity<Double> findAllBuyFormHasProduct(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProductPage();
            double sizePage = findAllBuyFormCart(buyFormHasProducts).size();
            double result = Math.ceil(sizePage / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("find-buyform")
    public ResponseEntity<BuyForm> findBuyFormBuy(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam("id") int id
    ) {
        if (header.equals(Token.tokenHeader)) {
            BuyFormHasProduct buyFormHasProduct = buyFormService.findBuyFormHasProductById(id);
            return new ResponseEntity<>(buyFormService.findById(buyFormHasProduct.getBuyFormId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("find-by-id")
    public ResponseEntity<BuyFormCart> findBuyFormBuyHasProductById(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam("id") int id
    ) {
        if (header.equals(Token.tokenHeader)) {
            for (BuyFormCart buyFormCart : buyFormCarts) {
                if (buyFormCart.getId() == id)
                    return new ResponseEntity<>(buyFormCart, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/delete")
    public ResponseEntity<Object> deleteBuyFormHasProduct(
            @RequestParam("id-product") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            boolean result = buyFormService.deleteBuyFormHasProduct(id);
            if (result) return new ResponseEntity<>("delete success", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete fail", HttpStatus.NOT_FOUND);
    }


    @PostMapping("/add-form")
    public ResponseEntity<Object> addBuyForm(
            @RequestBody BuyForm buyForm,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            if (!BuyFormDao.checkEmail(buyForm.getEmail()))
                return new ResponseEntity<>("email is not correct", HttpStatus.BAD_REQUEST);
            if (!BuyFormDao.checkPhone(String.valueOf(buyForm.getPhoneNumber())))
                return new ResponseEntity<>("phone number is not correct", HttpStatus.BAD_REQUEST);
            buyForm.setStatus(true);
            buyForm.setChecked(false);
            buyForm.setDate(LocalDate.now());
            buyForm.setProducts(null);
            if (buyFormService.saveBuyForm(buyForm))
                return new ResponseEntity<>(buyForm, HttpStatus.OK);
        }
        return new ResponseEntity<>("saved fail", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/add-products")
    public ResponseEntity<String> addProductsToBuyForm(
            @RequestBody List<Cart> carts,
            @RequestParam(value = "buy-form-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            BuyForm buyForm = buyFormService.findById(id);
            if (buyForm == null)
                return new ResponseEntity<>("buy form id is wrong", HttpStatus.BAD_REQUEST);
            List<Product> products = new ArrayList<>();
            carts.forEach(cart -> {
                Product product = productService.findById(cart.getProduct().getId());
                products.add(product);
            });
            buyForm.setProducts(products);
            buyForm.setStatus(true);
            buyFormService.saveBuyForm(buyForm);
            carts.forEach(cart -> {

                BuyFormHasProduct buyFormHasProduct = buyFormService.findByBuyFormAndProduct(buyForm, cart.getProduct());
                buyFormHasProduct.setQuantity(cart.getQuantity());
                buyFormHasProduct.setStatus(true);
                buyFormHasProduct.setSoldDate(LocalDate.now());
                buyFormService.updateBuyFormHasProduct(buyFormHasProduct);
            });
//            ===================== send-email==========================================

            String email = "haphatsmarthome@gmail.com";
            String title = "Đơn mua hàng mới";
            String content = "Bạn có đơn đặt hàng mới: MÃ " + buyForm.getId();
            user.setEmailAddress(email);
            boolean result = sendMailService.sendMail(user, title, content);
            if (result)
                return new ResponseEntity<>("Congratulations! Your mail has been send to the user.", HttpStatus.OK);
            //==========================================================================
            return new ResponseEntity<>("add products success", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

/*package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.Cart;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/public/cart")
public class CartController {

    private static final Logger LOGGER = Logger.getLogger(CartController.class.getName());

    @Autowired
    ProductService_Impl productService_imp;

    @GetMapping("/find-all-cart")
    public ResponseEntity<List<Cart>> findAllCart(
            HttpSession session,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");Map<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        List<Cart> carts = new ArrayList<>();
        if (cartItems == null) return new ResponseEntity<>(null, HttpStatus.OK);
        for (Map.Entry<Integer, Cart> cartEntry : cartItems.entrySet()) {
            carts.add(cartEntry.getValue());
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);

    }

    @GetMapping("/total")
    public ResponseEntity<Integer> findCartTotalPrice(HttpSession session, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");int totalPriceProduct = (int) session.getAttribute("myCartTotal");
        return new ResponseEntity<>(totalPriceProduct, HttpStatus.OK);

    }

    @GetMapping("/size")
    public ResponseEntity<Integer> findCartTotal(HttpSession session, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");int totalProduct = (int) session.getAttribute("myCartNum");
        return new ResponseEntity<>(totalProduct, HttpStatus.OK);

    }

    @RequestMapping(value = "/addProductCart", method = RequestMethod.GET)
    public ResponseEntity<Object> addProductCartCookie(
            @RequestParam("productId") int productId,
            HttpServletResponse response,
            HttpServletRequest request

    ) throws JSONException {

        System.out.println("dm");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");HashMap<Integer, Cart> cartItems = new HashMap<>();

        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        Product product = productService_imp.findById(productId);
        if (product != null) {
            if (cartItems.containsKey(productId)) {
                Cart item = cartItems.get(productId);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);
                cartItems.put(productId, item);
            } else {
                Cart item = new Cart();
                item.setQuantity(1);
                item.setProduct(product);
                cartItems.put(productId, item);
            }
        }


        JSONObject cartObject = new JSONObject();
        cartObject.put("myCartItems", cartItems);
        cartObject.put("myCartTotal", totalPrice(cartItems));
        cartObject.put("myCartNum", cartItems.size());
        System.out.println("hi " + cartObject);

        Cookie cartItem = new Cookie("myCartItems", cartObject.getJSONObject("myCartItems").toString());
        Cookie myCartTotal = new Cookie("myCartItems", cartObject.getJSONObject("myCartItems").toString());
        Cookie myCartNum = new Cookie("myCartItems", cartObject.getJSONObject("myCartItems").toString());

        response.addCookie(cartItem);
        response.addCookie(myCartTotal);
        response.addCookie(myCartNum);

        return new ResponseEntity<>(cartObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateProductCart", method = RequestMethod.GET)
    public ResponseEntity<String> updateProductCart(
            HttpSession session,
            @RequestParam("productId") int productId,
            @RequestParam("quality") int quality,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        if (cartItems.containsKey(productId)) {
            Cart item = cartItems.get(productId);
            item.setQuantity(quality);
        }

        session.setAttribute("myCartItems", cartItems);
        return new ResponseEntity<>("update succsess", HttpStatus.OK);
    }

    @RequestMapping(value = "/removeProductCart", method = RequestMethod.GET)
    public ResponseEntity<String> removeProductCart(
            HttpSession session,
            @RequestParam("productId") int productId,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");response.setHeader("access-control-allow-credentials" , "true");HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        if (cartItems.containsKey(productId)) {
            cartItems.remove(productId);
        }

        session.setAttribute("myCartItems", cartItems);

        session.setAttribute("myCartTotal", totalPrice(cartItems));

        session.setAttribute("myCartNum", cartItems.size());

        return new ResponseEntity<>("remove succsess", HttpStatus.OK);
    }

    public double totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getSaleCost() * list.getValue().getQuantity();
        }
        return count;
    }
}*/


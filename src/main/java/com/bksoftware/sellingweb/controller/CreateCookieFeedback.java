package com.bksoftware.sellingweb.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCookieFeedback extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        // Create cookies for first and last names.
        Cookie username = new Cookie("username", request.getParameter("username"));
        Cookie email = new Cookie("email", request.getParameter("email"));
        Cookie text = new Cookie("text", request.getParameter("text"));
        Cookie time = new Cookie("time", request.getParameter("time"));
        Cookie product_details_id = new Cookie("product_details_id", request.getParameter("product_details_id"));
        // Set expiry date after 24 Hrs for both the cookies.
        username.setMaxAge(60 * 60 * 24);
        email.setMaxAge(60 * 60 * 24);

        // Add both the cookies in the response header.
        response.addCookie(username);
        response.addCookie(email);
        response.addCookie(text);
        response.addCookie(time);
        response.addCookie(product_details_id);

    }
}

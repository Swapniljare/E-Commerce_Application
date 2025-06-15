package com.store.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.store.dao.ProductDAO;
import com.store.model.Cart;
import com.store.model.Product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add-to-cart") // consistent with your JSP link
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productIdParam = request.getParameter("id");

        try {
            if (productIdParam == null || productIdParam.trim().isEmpty()) {
                throw new NumberFormatException("Missing productId");
            }

            int productId = Integer.parseInt(productIdParam);

            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);

            if (product != null) {
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }

                cart.addProduct(product);
                session.setAttribute("cart", cart);
                response.sendRedirect("cart.jsp"); // or wherever you want to redirect after add
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid productId: " + productIdParam);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        }
    }
}

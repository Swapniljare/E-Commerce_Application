package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.CartItem;
import com.store.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }
    
    private boolean isAjax(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(requestedWith);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.getProductById(productId);

            boolean found = false;
            for (CartItem item : cart) {
                if (item.matchesProduct(productId)) {
                    item.increaseQuantity();
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(product));
            }
            
            if (!isAjax(request)) {
                response.sendRedirect("cart.jsp"); // only if not AJAX
            } else {
                response.setStatus(HttpServletResponse.SC_OK); // respond silently
            }
            return;

        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            for (CartItem item : cart) {
                if (item.matchesProduct(productId)) {
                    item.updateQuantity(quantity);
                    break;
                }
            }

        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            cart.removeIf(item -> item.matchesProduct(productId));
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}

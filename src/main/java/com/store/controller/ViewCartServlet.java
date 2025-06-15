package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        List<Product> cartItems = new ArrayList<>();
        Map<Integer, Integer> quantities = new HashMap<>();
        double total = 0.0;

        if (cart != null && !cart.isEmpty()) {
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                Product product = productDAO.getProductById(productId);
                if (product != null) {
                    cartItems.add(product);
                    quantities.put(productId, quantity);
                    total += product.getPrice() * quantity;
                }
            }
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("quantities", quantities);
        request.setAttribute("total", total);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}

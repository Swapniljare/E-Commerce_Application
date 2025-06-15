package com.store.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart != null && cart.containsKey(productId)) {
            int quantity = cart.get(productId);

            if ("increase".equals(action)) {
                cart.put(productId, quantity + 1);
            } else if ("decrease".equals(action)) {
                if (quantity > 1) {
                    cart.put(productId, quantity - 1);
                } else {
                    cart.remove(productId);
                }
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("viewCart"); // Keep consistent with ViewCartServlet
    }
}

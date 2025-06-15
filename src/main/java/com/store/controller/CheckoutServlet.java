package com.store.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Clear the session cart
        HttpSession session = request.getSession();
        session.removeAttribute("cart");

        request.setAttribute("message", "✅ Thank you for your purchase!");
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }
}

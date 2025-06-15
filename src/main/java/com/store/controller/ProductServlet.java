package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = productDAO.getAllProducts();

        System.out.println("Fetched products: " + products.size());  // ✅ Console print

        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);  // ✅ Forward to index.jsp
    }
}

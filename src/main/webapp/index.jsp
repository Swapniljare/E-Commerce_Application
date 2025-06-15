<%@ page import="java.util.*, com.store.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList<>();
        session.setAttribute("cart", cart);
    }

    String productId = request.getParameter("productId");
    if (productId != null) {
        int pid = Integer.parseInt(productId);
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product p : products) {
                if (p.getId() == pid) {
                    cart.add(p);
                    break;
                }
            }
        }
        response.sendRedirect("index.jsp"); // redirect to clear param
        return;
    }
%>

<html>
<head>
    <title>Store</title>
    <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 15px;
        }
        .product-card {
            padding: 10px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 5px gray;
            text-align: center;
        }
        .product-card img {
            width: 100%;
            height: 180px;
            object-fit: contain;
        }
        .add-btn {
            background: #28a745;
            color: #fff;
            padding: 8px 12px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h2>🛍️ My E-Commerce Store</h2>

<div class="product-grid">
    <c:forEach var="product" items="${products}">
        <div class="product-card">
            <img src="${product.imageUrl}" alt="${product.name}">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <strong>₹${product.price}</strong><br>
            <a href="index.jsp?productId=${product.id}">
                <button class="add-btn">Add to Cart</button>
            </a>
        </div>
    </c:forEach>
</div>

<h3>🛒 Cart Summary</h3>
<ul>
<%
    double total = 0;
    for (Product p : cart) {
        total += p.getPrice();
%>
    <li><%= p.getName() %> - ₹<%= p.getPrice() %></li>
<% } %>
</ul>
<p><strong>Total: ₹<%= total %></strong></p>
</body>
</html>

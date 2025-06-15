<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Your Shopping Cart 🛒</h1>
        <nav>
            <a href="products">Continue Shopping</a>
        </nav>
    </header>

    <main>
        <c:choose>
            <c:when test="${not empty cartItems}">
                <table>
                    <tr>
                        <th>Image</th>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Qty</th>
                        <th>Subtotal</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="item" items="${cartItems}">
                        <c:set var="qty" value="${quantities[item.id]}" />
                        <tr>
                            <td><img src="${item.imageUrl}" height="60"/></td>
                            <td>${item.name}</td>
                            <td>₹${item.price}</td>
                            <td>
                                <form action="updateCart" method="post" class="qty-form">
                                    <input type="hidden" name="productId" value="${item.id}" />
                                    <button type="submit" name="action" value="decrease" <c:if test="${qty <= 1}">disabled</c:if>>−</button>
                                    <span class="quantity">${qty}</span>
                                    <button type="submit" name="action" value="increase">+</button>
                                </form>
                            </td>
                            <td>₹${item.price * qty}</td>
                            <td>
                                <form action="removeFromCart" method="post">
                                    <input type="hidden" name="productId" value="${item.id}" />
                                    <button type="submit" class="remove-btn">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <h3>Total: ₹${total}</h3>
            </c:when>
            <c:otherwise>
                <p>Your cart is empty.</p>
            </c:otherwise>
        </c:choose>
    </main>
</div>
</body>
</html>

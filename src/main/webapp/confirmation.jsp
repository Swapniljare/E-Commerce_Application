<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
    <h1><%= request.getAttribute("message") %></h1>
    <a href="products">🔙 Back to Home</a>
</body>
</html>

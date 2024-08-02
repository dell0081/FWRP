<%-- Author: Damien Smith --%>
<%@page import="Model.ItemDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                margin-top: 20px;
            }
            table {
                margin-top: 20px;
                width: 100%;
            }
            .centered {
                margin-left: auto;
                margin-right: auto;
            }
            .mt-3 {
                margin-top: 1rem;
            }
            .btn-spacing {
                margin-right: 5px;
            }
            .table th, .table td {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container text-center">
            <h1>Welcome to the Food Waste Reduction Platform</h1>
            <div class="text-right mb-3">
                <a href="LogoutServlet" class="btn btn-danger">Logout</a>
            </div>
            <h3>View Your Cart</h3>
            <p>Confirm the items you wish to purchase from your cart.</p>
        </div>
        <div class="container mt-3">
            <form action="/FWRP/ClaimItemServlet" method="post">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Item Name</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            List<ItemDTO> cart = (List<ItemDTO>) session.getAttribute("cart");
                            if (cart != null && !cart.isEmpty()) {
                                for (ItemDTO item : cart) {
                                    if (item != null) {
                        %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getItemQuantity() %></td>
                            <td>$<%= item.getPrice() %></td>
                        </tr>
                        <% 
                                    }
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="3" class="text-center">Your cart is empty.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="text-center">
                    <% if (cart != null && !cart.isEmpty()) { %>
                        <button type="submit" class="btn btn-success btn-spacing">Confirm Purchase</button>
                    <% } %>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
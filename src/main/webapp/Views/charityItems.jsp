<%-- Author: Damien Smith --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.ItemDTO"%>
<%@page import="DataAccessLayer.CharityDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items Available for Charity</title>
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
            <%
                String purchaseSuccess = (String) request.getAttribute("purchaseSuccess");
                if (purchaseSuccess != null && !purchaseSuccess.isEmpty()) {
            %>
            <div class="alert alert-success" role="alert">
                <%= purchaseSuccess %>
            </div>
            <% } %>
            <h1>Charity - Food Waste Reduction Platform</h1>
            <div class="text-right mb-3">
                <a href="/FWRP/LogoutServlet" class="btn btn-danger">Logout</a>
            </div>
            <h3>Items Available for Charity</h3>
            <p>Select items you wish to claim.</p>
            <%
                String errorMessage = (String) request.getAttribute("error");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
            <% } %>
        </div>
        <div class="container mt-3">
            <form action="/FWRP/ClaimItemsServlet" method="post" onsubmit="return validateForm()">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Claim</th>
                            <th>Retailer</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("itemsForCharity");
                            if (items != null && !items.isEmpty()) {
                                for (ItemDTO item : items) {
                        %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getItemQuantity() %></td>
                            <td>$<%= item.getPrice() %></td>
                            <td>
                                <input type="checkbox" name="inventory_id" value="<%= item.getItemId() %>">
                            </td>
                            <td><%= item.getRetailerName() %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="5" class="text-center">No items available for charity.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-spacing">Claim Selected Items</button>
                </div>
            </form>
        </div>
        <script>
            function validateForm() {
                var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
                if (checkboxes.length === 0) {
                    alert('Please select at least one item to claim.');
                    return false;
                }
                return true;
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>

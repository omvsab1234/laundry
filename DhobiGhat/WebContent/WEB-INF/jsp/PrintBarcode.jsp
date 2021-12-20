<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
        <title>Perclean Laundry </title>
 
</head>
<body>
 
                <div class="container">                                                           
                    <div class="row">
                        <div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
                            <div class="statbox widget box box-shadow">
                                <div class="widget-header">
                                    <div class="row">
                                        <div class="col-xl-12 col-md-12 col-sm-12 col-12">
                                            <h4>Print Barcode</h4>
                                        </div>                 
                                    </div>
                                </div>

                                <div class="widget-content widget-content-area">                                                                    
                                    <!--  BEGIN CONTENT AREA  -->                                                             
                                    <div class="table">
                                        <table id="example" class="display" style="width:100%">
                                            <thead>
                                                <tr>
                                                	<th style="display: none;">Customer Id</th>
                                                    <th>Invoice No</th>
                                                    <th style="display: none;">Order Id</th>
                                                    <th>Order Date</th>
                                                    <th>Due Date</th>
                                                    <th>Total Qty</th>
                                                    <th>Total Amount</th>
                                                    <th>Paid</th>
                                                    <th>Remaining</th>
                                                    <th>Barcode</th>
                                                </tr>
                                            </thead><!-- AllOrderList -->
                                            <tbody>
                                                <c:forEach var="v" items="${AllOrderList}">
                                                <tr class="text-center">
                                              	    <td style="display: none;"><c:out value="${v.cId}"></c:out></td>
                                                    <td><c:out value="${v.invoiceNo}"></c:out></td>
                                                    <td style="display: none;"><c:out value="${v.orderId}"></c:out></td>
                                                    <td><c:out value="${v.orderDate}"></c:out></td>
                                                    <td><c:out value="${v.dueDate}"></c:out></td>
                                                    <td><c:out value="${v.totalQuantity}"></c:out></td>
                                                    <td><c:out value="${v.totalAmount}"></c:out></td>
                                                    <td><c:out value="${v.amountPaid}"></c:out></td>
                                                    <td><c:out value="${v.amountRemaining}"></c:out></td>
                                                    <td class="text-center"><a href="printOrderOfOrder.html?orderId=${v.orderId}" target="_blank">
                                                    	<button class="btn btn-primary mb-2 mr-2" type="submit">Print Barcode</button></a>
                                                    </td>
                                                </tr>
                                               </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                 	<th style="display: none;">Customer Id</th>
                                                    <th>Invoice No</th>
                                                    <th style="display: none;">Order Id</th>
                                                    <th>Order Date</th>
                                                    <th>Due Date</th>
                                                    <th>Total Qty</th>
                                                    <th>Total Amount</th>
                                                    <th>Paid</th>
                                                    <th>Remaining</th>
                                                    <th>Barcode</th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>      
                            </div>
                        </div>
                    </div>                 
                </div>

</body>
</html>
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
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.png"/>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
    
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="plugins/animate/animate.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="assets/css/tables/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
        <!-- END PAGE LEVEL STYLES -->
        <!--  BEGIN CUSTOM STYLE FILE  -->
        <link href="assets/css/scrollspyNav.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/components/custom-modal.css" rel="stylesheet" type="text/css" />
        <!--  END CUSTOM STYLE FILE  -->
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-10 col-md-10 col-sm-12 col-12">
								<h4>All Customers</h4>
							</div>
							<div class="col-xl-2 col-md-2 col-sm-12 col-12">
								<h4><a href="AllCustomerReport.html" target="_blank">Print PDF</a></h4>
							</div>
											
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<!--  BEGIN CONTENT AREA  -->
						<div class="table">
							<table id="example" class="display" style="width: 100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Balance Rs</th>
									</tr>
								</thead>
								<tbody id="customerDetails">
									<c:forEach var="v" items="${CustomerList}">
										<tr>
											<td><c:out value="${v.cId}"></c:out></td>
											<td><c:out value="${v.cName}"></c:out></td>
											<td><c:out value="${v.cMobile}"></c:out></td>
											<td><c:out value="${v.cAddress}"></c:out></td>
											<td><c:out value="${v.cAmount }"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th class="checkbox-column">ID</th>
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Balance Rs</th>
										<th>Order</th>
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
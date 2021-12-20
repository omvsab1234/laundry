<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function onOrderIdChange(){
		var orderId = $("#od").val();
		var invoiceNo = $("#invoice").val();
		$.getJSON('getOrderChiAllDetail.html', {
			orderId : orderId,
			invoiceNo : invoiceNo
		}).done(function(data) {
			$("#invoiceNo").val(data[0].invoiceNo);
			$("#orderId").val(data[0].orderId);
			var table = document.getElementById("orderDetailsTable");
			for (var i = 0; i < data.length; i++) {
				var row =  table.insertRow(0); 
				  var cell1 = row.insertCell(0);   var cell2 = row.insertCell(1); 
				  var cell3 = row.insertCell(2);   var cell4 = row.insertCell(3);
				  var cell5 = row.insertCell(4);   //var cell6 = row.insertCell(5);
				  
				  //cell1.innerHTML = data[i].saparateId;
				  cell1.innerHTML = data[i].clothType;  
				  cell2.innerHTML = data[i].serviceType;
				  cell3.innerHTML = data[i].Status;   
				  cell4.innerHTML = data[i].deleveredDate;
				  if(data[i].Status=='Not Ready'){
				cell5.innerHTML = "<input type='checkbox' onclick='onCheckBoxClick(this.parentNode.parentNode)' value='"+data[i].saparateId+"' name='SelectedId' id='CheckBox"+i+"'>";
				}
				if(data[i].Status=='Ready'){
					  cell5.innerHTML = "<input type='checkbox' disabled checked onclick='onCheckBoxClick(this.parentNode.parentNode)' >";
				}
				 if(data[i].Status=='Delivered'){
					  cell5.innerHTML = "<input type='checkbox' checked disabled onclick='onCheckBoxClick(this.parentNode.parentNode)' >";
				}
				 
			}
		});
		
		var orderStatus = $('#orderStatus').val();
		if(orderStatus=='Ready'){
			document.getElementById("SaveReadyOrder").disabled = true;
		}
		if(orderStatus=='Delivered'){
			document.getElementById("DeliverOnlyReadyCloths").disabled = true;
			document.getElementById("SaveReadyOrder").disabled = true;
		}
	}
</script><!-- $("#MainRate").val(data[0].rate); -->
<script type="text/javascript">
	function onCheckBoxClick(a){
		var i = a.children[0].textContent;
		///alert("Okkk "+a.children[0].textContent);
		var cb = document.getElementById("CheckBox"+i);
		if(cb.checked == true){
			//alert(i+"th checkbox is Checked");
		}else{
			//alert(i+"th checkbox is Unchecked");
		}
	}
</script>
<script type="text/javascript">
   function onBodyLoad(){
	   if($('#od').val()!=''){
			$('#od').trigger('change');
	   		$("#cId2").val($('#cId').val());
	   		$("#Amt").val($('#cAmount').val());
	   		$("#cMobile2").val($('#cMobile').val());
		}
}
</script>
<style type="text/css">
#table-wrapper {
	position: relative;
}

#table-scroll {
	height: 200px;
	overflow: auto;
	margin-top: 5px;
}

#table-wrapper table {
	width: 100%;
}

#table-wrapper table thead th .text {
	position: absolute;
	top: -20px;
	z-index: 2;
	height: 20px;
	width: 35%;
	border: 1px solid red;
}
}
</style>
</head>
<body onload="onBodyLoad()">
	<div class="container pt-4">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="row col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Customer Details</h4>
							</div>
						</div>
					</div>
					<div class="widget-content widget-content-area">
						<div class="form-row" style="border-right: 2px solid #eeeeee;">
							<c:forEach var="v" items="${CustomerInfo}">
								<div class="col-md-2 mb-2">
									<label>Name</label>
									<div class="details" style="display: none;">
										<c:out value="${v.cId }"></c:out>
										<input id="cId" value="${v.cId }">
									</div>
									<div class="details">
										<c:out value="${v.cName}"></c:out>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Contact Number</label>
									<div class="details">
										<c:out value="${v.cMobile}"></c:out>
										<input id="cMobile" value="${v.cMobile }" type="hidden">
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Address</label>
									<div class="details">
										<c:out value="${v.cAddress}"></c:out>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>All Balance</label>
									<div class="details">
										<c:out value="${v.cAmount }"></c:out>
										<input id="cAmount" value="${v.cAmount }" type="hidden">
									</div>
								</div>
							</c:forEach>
						</div>
						 <div class="form-row" style="border-bottom: 2px solid #eeeeee; border-right: 2px solid #eeeeee;">
							<c:forEach var="v" items="${AllOrderDetails}">
								<div class="col-md-2 mb-2">
									<label>Tot Cloths</label>
									<div class="details">
										<c:out value="${v.totalQuantity}"></c:out>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Ready Qty</label>
									<div class="details">
										<c:out value="${v.ReadyQty}"></c:out>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Not Ready</label>
									<div class="details">
										<c:out value="${v.NotReadyQty}"></c:out>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Delivered</label>
									<div class="details">
										<c:out value="${v.DeliveredQty}"></c:out>
									</div>
									<input type="hidden" id="invoice" value="${v.invoiceNo}">
										<input type="hidden" id="od" value="${v.orderId}" 
										 onchange="onOrderIdChange()">
								</div>
								<div class="col-md-2 mb-2">
									<label>Status</label>
									<div class="details">
										<c:out value="${v.orderStatus }"></c:out>
										<input id="orderStatus" value="${v.orderStatus }" type="hidden">
									</div>
								</div>
								
							</c:forEach>
						</div>

						<div class="container pt-3" style="border-bottom: 2px solid #ffffff;"></div>
						<!--  -->
					<form action="SaveReadyClothsInOrder.html" method="post">
							<div id="table-wrapper">
								<div id="table-scroll">
									<table id="">
										<thead>
											<tr class="bg-warning">
												<th>Cloth Type</th>
												<th>Service Type</th>
												<th>Status</th>
												<th>Delivered Date</th>
												<th><input type="checkbox" onclick="onSelctClick()" id="cbAll" value="Select All"> Select All</th>
											</tr>
										</thead>
										
										<tbody id="orderDetailsTable">
											
										</tbody>
										<tfoot>
											<tr class="bg-success">
												<th>Cloth Type</th>
												<th>Service Type</th>
												<th>Status</th>
												<th>Delivered Date</th>
												<th>Select All</th>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						<input type="hidden" name="invoiceNo" id="invoiceNo">
						<input type="hidden" name="orderId" id="orderId">
						<input type="hidden" name="cMobile2" id="cMobile2">
						<br>
						<div class="form-row">
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-primary mb-4 mr-2" name="SaveReadyOrder" id="SaveReadyOrder"
									type="submit" required>
									<b>Save Ready Cloths</b>
								</button>
							</div>
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-success mb-4 mr-2" id="DeliverOnlyReadyCloths"
									data-toggle="modal" data-target="#exampleModal" type="button"
									required>
									<b>Deliver Only Ready Cloths</b>
								</button>
							</div>
							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">
												<b>Deliver Cloths</b>
											</h5>
											<button type="submit" class="close" data-dismiss="modal"
												aria-label="Close">
												<svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
													width="24" height="24" viewBox="0 0 24 24" fill="none"
													stroke="currentColor" stroke-width="2"
													stroke-linecap="round" stroke-linejoin="round"
													class="feather feather-x">
													<line x1="18" y1="6" x2="6" y2="18"></line>
													<line x1="6" y1="6" x2="18" y2="18"></line></svg>
											</button>

										</div>
										<div class="modal-body">
											<div class="n-chk">
												<label class="new-control new-radio radio-primary">
													<input type="radio" class="new-control-input"
													name="custom_radio_1" checked id="CollectPayment">
													<span class="new-control-indicator"></span>Collect Payment
												</label>
												<!-- onclick="document.getElementById('Paid').disabled = false" -->
												<div class="form-row">
													<div class="col-xl-6">
														<label>Amount</label> <input
															class="form-control form-control-sm" type="text" id="Amt"
															style="color: #000;" readonly name="balanceAmt">
														<input class="form-control form-control-sm" type="hidden"
															id="cId2" style="color: #000;" readonly name="cId2"
															value="${customerId}">
													</div>
													<div class="col-xl-6">
														<label>Paid</label> <input
															class="form-control form-control-sm" type="text"
															id="Paid" autocomplete="off" onclick="onPaidClick()"
															style="color: #000;" name="paidAmt" value="0"
															onchange="onPaidChange()">
													</div>
												</div>
											</div>
											<div class="n-chk mt-4">
												<label class="new-control new-radio"> <input
													type="radio" class="new-control-input"
													name="custom_radio_1" onclick="onSkipPaymentClick()">
													<span class="new-control-indicator"></span>Skip Payment
												</label>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn" data-dismiss="modal">
												<i class="flaticon-cancel-12"></i> Discard
											</button>
											<button type="submit" class="btn btn-primary" name="DeliverClothOrder">
												Deliver</button>
										</div>
								</div>
							</div>
						</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function onSelctClick() {
		var checkBox1 = document.getElementById("cbAll");
		if (checkBox1.checked == true) {
			//alert("checked");
			var items = document.getElementsByName('SelectedId');
			for (var i = 0; i < items.length; i++) {
				if (items[i].type == 'checkbox')
					items[i].checked = true;
			}
		}else{
			//alert("unchecked");
			var items = document.getElementsByName('SelectedId');
			for (var i = 0; i < items.length; i++) {
				if (items[i].type == 'checkbox')
					items[i].checked = false;
			}
		}
	}
</script>
	
	<script type="text/javascript">
		function onSkipPaymentClick() {
			document.getElementById("Paid").value = 0;
		}

		function onPaidClick() {
			document.getElementById("Paid").select();
			document.getElementById("CollectPayment").checked = true;
		}

		function onPaidChange() {
			var BalanceAmt = parseDouble($("#Amt").val());
			var Paid = parseDouble($("#Paid").val());
			if (Paid > BalanceAmt) {
				alert("Enter Amount Less than or Equal to " + BalanceAmt);
				document.getElementById("Paid").focus();
			}
		}
	</script>
</body>
</html>
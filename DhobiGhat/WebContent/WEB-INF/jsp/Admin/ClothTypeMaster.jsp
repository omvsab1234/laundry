<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

</head>
<body>
	<div class="row layout-top-spacing">
		<div id="custom-styles"
			class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
			<div class="statbox widget box box-shadow">
				<div class="widget-header">
					<div class=" row col-xl-12 col-md-12 col-sm-12 col-12">
						<h4>Cloth Type</h4>
					</div>
				</div>
				<div class="widget-content widget-content-area">
					<form action="SaveClothType.html" method="post">
						<div class="form-row">
							<div class="col-md-12 mb-4">
								<label>Enter Cloth Type</label> <input type="hidden"
									class="form-control" placeholder="Id" name="clothTypeID"
									id="ClothTypeID"> <input type="text"
									class="form-control" placeholder="New Item" value=""
									name="clothType" id="ClothType" required>
							</div>
						</div>
						<p style="color: red;">${errorMsgClothType}</p>
						<div class="text-right">
							<button for="validationCustom05"
								class="form-control btn btn-primary mb-2 mr-2" type="submit"
								required id="btnAddCloth" name="btnAddCloth">
								<svg width="1em" height="1em" viewBox="0 0 19 19"
									class="bi bi-plus-square" fill="currentColor"
									xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
										d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
                                            <path fill-rule="evenodd"
										d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                        </svg>
								Add
							</button>
						</div>
						<div class="form-row">
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-primary mb-4 mr-2"
									type="submit" id="btnUpdateCloth" required
									style="display: none;" name="btnUpdateCloth">
									<svg width="1em" height="1em"
										xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
										viewBox="0 0 550 550" fill="currentColor">
                                                <g>
                                                    <path
											d="M106.6,149.3V42.7h279.2l62.2,62.2v364.5l-106.7,0V512l149.3,0V87.2L403.5,0H64v149.3H106.6L106.6,149.3z M341.3,21.3v128
                                                        h128v-42.7l-85.3,0V21.3H341.3L341.3,21.3z M192,490.7V512c94.3,0,170.7-76.4,170.7-170.7c0-94.3-76.4-170.7-170.7-170.7
                                                        c-94.3,0-170.7,76.4-170.7,170.7C21.3,435.6,97.7,512,192,512V490.7v-21.3c-35.4,0-67.3-14.3-90.5-37.5
                                                        C78.3,408.6,64,376.8,64,341.3c0-35.4,14.3-67.3,37.5-90.5c23.2-23.2,55.1-37.5,90.5-37.5c35.4,0,67.3,14.3,90.5,37.5
                                                        c23.2,23.2,37.5,55.1,37.5,90.5c0,35.4-14.3,67.3-37.5,90.5c-23.2,23.2-55.1,37.5-90.5,37.5V490.7z M132.4,367.1l59.6-59.6
                                                        l59.6,59.6l30.2-30.2L192,247.2l-89.8,89.8L132.4,367.1L132.4,367.1z M170.7,288v138.7h42.7V288H170.7z" />
                                                </g>
                                            </svg>
									<b> Update</b>
								</button>
							</div>
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-success mb-4 mr-2"
									id="btnDeleteCloth" type="submit" required
									style="display: none;" name="btnDeleteCloth">
									<svg width="1em" height="1em" viewBox="0 0 18 18"
										class="bi bi-trash" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                                <path
											fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                              </svg>
									<b> Delete</b>
								</button>
							</div>
						</div>
					</form>
					<div class="table pt-4">
						<table id="example1" class="display" style="width: 100%;">
							<thead>
								<tr>
									<th>Sr No</th>
									<th>Particular</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${ClothTypeList}">
									<tr onclick="onClothTrClick(this)">
										<td><c:out value="${v.clothTypeID }"></c:out></td>
										<td><c:out value="${v.clothType }"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Sr No</th>
									<th>Particular</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="custom-styles"
			class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
			<div class="statbox widget box box-shadow">
				<div class="widget-header">
					<div class="row">
						<div class="col-xl-12 col-md-12 col-sm-12 col-12">
							<h4>Service Type</h4>
						</div>
					</div>
				</div>
				<div class="widget-content widget-content-area">
					<form action="SaveServiceType.html" method="post">
						<div class="form-row">
							<div class="col-md-12 mb-4">
								<label>Enter Service Type</label> <input type="hidden"
									class="form-control" placeholder="New Item" id="ServiceTypeID"
									name="serviceTypeID"> <input type="text"
									class="form-control" placeholder="New Item"
									id="ServiceTypeName" name="serviceTypeName" required>
							</div>
						</div>
						<p style="color: red;">${errorMsgServiceType}</p>
						<div class="text-right">
							<button for="validationCustom05"
								class="form-control btn btn-primary mb-2 mr-2" type="submit"
								required name="btnAddService" id="btnAddService">
								<svg width="1em" height="1em" viewBox="0 0 19 19"
									class="bi bi-plus-square" fill="currentColor"
									xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
										d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
                                            <path fill-rule="evenodd"
										d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                        </svg>
								Add
							</button>
						</div>
						<div class="form-row">
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-primary mb-4 mr-2"
									type="submit" id="btnUpdateService" required
									style="display: none;" name="btnUpdateCloth">
									<svg width="1em" height="1em"
										xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
										viewBox="0 0 550 550" fill="currentColor">
                                                <g>
                                                    <path
											d="M106.6,149.3V42.7h279.2l62.2,62.2v364.5l-106.7,0V512l149.3,0V87.2L403.5,0H64v149.3H106.6L106.6,149.3z M341.3,21.3v128
                                                        h128v-42.7l-85.3,0V21.3H341.3L341.3,21.3z M192,490.7V512c94.3,0,170.7-76.4,170.7-170.7c0-94.3-76.4-170.7-170.7-170.7
                                                        c-94.3,0-170.7,76.4-170.7,170.7C21.3,435.6,97.7,512,192,512V490.7v-21.3c-35.4,0-67.3-14.3-90.5-37.5
                                                        C78.3,408.6,64,376.8,64,341.3c0-35.4,14.3-67.3,37.5-90.5c23.2-23.2,55.1-37.5,90.5-37.5c35.4,0,67.3,14.3,90.5,37.5
                                                        c23.2,23.2,37.5,55.1,37.5,90.5c0,35.4-14.3,67.3-37.5,90.5c-23.2,23.2-55.1,37.5-90.5,37.5V490.7z M132.4,367.1l59.6-59.6
                                                        l59.6,59.6l30.2-30.2L192,247.2l-89.8,89.8L132.4,367.1L132.4,367.1z M170.7,288v138.7h42.7V288H170.7z" />
                                                </g>
                                            </svg>
									<b> Update</b>
								</button>
							</div>
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-success mb-4 mr-2"
									id="btnDeleteService" type="submit" required
									style="display: none;" name="btnDeleteCloth">
									<svg width="1em" height="1em" viewBox="0 0 18 18"
										class="bi bi-trash" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                                <path
											fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                              </svg>
									<b> Delete</b>
								</button>
							</div>
						</div>
					</form>
					<div class="table pt-4">
						<table id="example2" class="display" style="width: 100%;">
							<thead>
								<tr>
									<th>Sr No</th>
									<th>Particular</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${ServiceTypeList }">
									<tr onclick="onServiceTrClick(this)">
										<td><c:out value="${v.serviceTypeID }"></c:out></td>
										<td><c:out value="${v.serviceTypeName }"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Sr No</th>
									<th>Particular</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="custom-styles"
			class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
			<div class="statbox widget box box-shadow">
				<div class="widget-header">
					<div class="row">
						<div class="col-xl-12 col-md-12 col-sm-12 col-12">
							<h4>Rate Master</h4>
						</div>
					</div>
				</div>
				<div class="widget-content widget-content-area">
					<form action="saveRateMaster.html" method="post">
						<div class="form-row">
							<div class="col-sm-6 mb-4">
								<label>Cloth Type</label> <input type="hidden" name="rateId"
									id="RateId"> <select
									class="form-control form-control-sm" placeholder="select"
									name="clothTypeID" id="clothTypeID">
									<option id="" value="">--Select Cloth Type--</option>
									<c:forEach items="${ClothList}" var="trl">
										<option value="${trl.key}">${trl.value}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-6 mb-2">
								<label>Service Type</label> <select
									class="form-control form-control-sm" placeholder="select"
									name="serviceTypeID" id="serviceTypeID">
									<option id="" value="">--Select Service Type--</option>
									<c:forEach items="${ServiceList}" var="trl">
										<option value="${trl.key}">${trl.value}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-6 mb-2">
								<label>Rate</label> <input type="text" class="form-control"
									placeholder="Enter Rate.." id="Rate" name="rate"
									pattern="[0-9]+$" autocomplete="off"
									onkeypress="return restrictAlphabets(event)" required>
							</div>
							<p style="color: red;">${errorMsgRateMaster}</p>
							<div class="col-sm-6 pt-4">
								<button for="validationCustom05"
									class="form-control btn btn-primary mb-2 mr-2" type="submit"
									name="btnAddRate" id="btnAddRate" required>
									<svg width="1em" height="1em" viewBox="0 0 19 19"
										class="bi bi-plus-square" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											fill-rule="evenodd"
											d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
                                                <path
											fill-rule="evenodd"
											d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                            </svg>
									Add
								</button>
							</div>
						</div>
						<div class="form-row">
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-primary mb-4 mr-2"
									type="submit" style="display: none;" name="btnUpdateRate"
									id="btnUpdateRate" required>
									<svg width="1em" height="1em"
										xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
										viewBox="0 0 550 550" fill="currentColor">
                                                <g>
                                                    <path
											d="M106.6,149.3V42.7h279.2l62.2,62.2v364.5l-106.7,0V512l149.3,0V87.2L403.5,0H64v149.3H106.6L106.6,149.3z M341.3,21.3v128
                                                        h128v-42.7l-85.3,0V21.3H341.3L341.3,21.3z M192,490.7V512c94.3,0,170.7-76.4,170.7-170.7c0-94.3-76.4-170.7-170.7-170.7
                                                        c-94.3,0-170.7,76.4-170.7,170.7C21.3,435.6,97.7,512,192,512V490.7v-21.3c-35.4,0-67.3-14.3-90.5-37.5
                                                        C78.3,408.6,64,376.8,64,341.3c0-35.4,14.3-67.3,37.5-90.5c23.2-23.2,55.1-37.5,90.5-37.5c35.4,0,67.3,14.3,90.5,37.5
                                                        c23.2,23.2,37.5,55.1,37.5,90.5c0,35.4-14.3,67.3-37.5,90.5c-23.2,23.2-55.1,37.5-90.5,37.5V490.7z M132.4,367.1l59.6-59.6
                                                        l59.6,59.6l30.2-30.2L192,247.2l-89.8,89.8L132.4,367.1L132.4,367.1z M170.7,288v138.7h42.7V288H170.7z" />
                                                </g>
                                            </svg>
									<b> Update</b>
								</button>
							</div>
							<div class="col-sm-6 mb-4">
								<button class="form-control  btn btn-success mb-4 mr-2"
									style="display: none;" name="btnDeleteRate" id="btnDeleteRate"
									required>
									<svg width="1em" height="1em" viewBox="0 0 18 18"
										class="bi bi-trash" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                                <path
											fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                              </svg>
									<b> Delete</b>
								</button>
							</div>
						</div>
					</form>
					<div class="table pt-4">
						<table id="example" class="display" style="width: 100%;">
							<thead>
								<tr>
									<th>Sr No</th>
									<th>Particular</th>
									<th>Service</th>
									<th>Rate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${RateList}">
									<tr onclick="onRateTrClick(this)">
										<td><c:out value="${v.rateId }"></c:out></td>
										<td><c:out value="${v.clothType}"></c:out></td>
										<td><c:out value="${v.serviceTypeName }"></c:out></td>
										<td><c:out value="${v.rate}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Sr No</th>
									<th>Particular</th>
									<th>Service</th>
									<th>Rate</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
	function onClothTrClick(a){
		$("#ClothTypeID").val(a.children[0].textContent);
		$("#ClothType").val(a.children[1].textContent);
		
		document.getElementById('btnAddCloth').style.display='none'
		document.getElementById('btnUpdateCloth').style.display='block'
		document.getElementById('btnDeleteCloth').style.display='block'
		
		$('#ClothType').focus();
	}
</script>
	<script type="text/javascript">
	function onServiceTrClick(a){
		$("#ServiceTypeID").val(a.children[0].textContent);
		$("#ServiceTypeName").val(a.children[1].textContent);
		
		document.getElementById('btnAddService').style.display='none'
		document.getElementById('btnUpdateService').style.display='block'
		document.getElementById('btnDeleteService').style.display='block'
		
		$('#ServiceTypeName').focus();
	}
</script>
	<script type="text/javascript">
	function onRateTrClick(a){
		$("#RateId").val(a.children[0].textContent);
		$("#clothTypeID").val(a.children[1].textContent);
		$("#serviceTypeID").val(a.children[2].textContent);
		$("#Rate").val(a.children[3].textContent);
		document.getElementById('btnAddRate').style.display='none'
		document.getElementById('btnUpdateRate').style.display='block'
		document.getElementById('btnDeleteRate').style.display='block'
		
		$('#PreferanceName').focus();
	}
</script>

	<script>
        $(document).ready(function() {
            App.init();
        });
    </script>
	<script src="assets/js/custom.js"></script>
	<!-- END GLOBAL MANDATORY SCRIPTS -->

	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="plugins/apex/apexcharts.min.js"></script>
	<script src="assets/js/dashboard/dash_1.js"></script>
	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="assets/js/jquery.dataTables.min.js"></script>
<!-- 	<script>
        $(document).ready(function() {
        $('#example').DataTable( {
        "scrollY": "200px" ,
        "scrollCollapse": true,
        "scrollX": true
        } );
        } );
    </script> -->
	<div class="col-md-4 mb-4">
		<button class="form-control  btn btn-success button-block mb-4 mr-2"
			data-toggle="modal" data-target="#exampleModal" type="submit"
			required>Bill Preview</button>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Bill Preview</h5>

					<button type="submit" class="close" data-dismiss="modal"
						aria-label="Close">
						<svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
							width="24" height="24" viewBox="0 0 24 24" fill="none"
							stroke="currentColor" stroke-width="2" stroke-linecap="round"
							stroke-linejoin="round" class="feather feather-x">
							<line x1="18" y1="6" x2="6" y2="18"></line>
							<line x1="6" y1="6" x2="18" y2="18"></line></svg>
					</button>

				</div>

				<div class="modal-footer">
					<button class="btn" data-dismiss="modal">
						<i class="flaticon-cancel-12"></i> Discard
					</button>
					<button type="button" class="btn btn-success">
						<svg width="1em" height="1em" viewBox="0 0 16 16"
							class="bi bi-printer" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
                                                            <path
								d="M11 2H5a1 1 0 0 0-1 1v2H3V3a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v2h-1V3a1 1 0 0 0-1-1zm3 4H2a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h1v1H2a2 2 0 0 1-2-2V7a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2h-1v-1h1a1 1 0 0 0 1-1V7a1 1 0 0 0-1-1z" />
                                                            <path
								fill-rule="evenodd"
								d="M11 9H5a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1zM5 8a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H5z" />
                                                            <path
								d="M3 7.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z" />
                                                          </svg>
						Print
					</button>
				</div>
			</div>
		</div>
	</div>


	<script>
        $(document).ready(function() {
            App.init();
        });
    </script>
	<script src="assets/js/custom.js"></script>
	<!-- END GLOBAL MANDATORY SCRIPTS -->

	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="plugins/apex/apexcharts.min.js"></script>
	<script src="assets/js/dashboard/dash_1.js"></script>
	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="assets/js/jquery.dataTables.min.js"></script>
</body>
</html>
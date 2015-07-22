<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<!-- ìë¨ ë¤ë¹ê²ì´ì ë° ì¸í´ë£¨ë -->
<jsp:include page="nav.jsp" flush = "false" />
<script src="./resources/js/itemcategorization.js"></script>
<script type="text/javascript">
$(document).ready(
		function(){var id = ['#Lcategory1','#Lcategory2'];getLcategory(id);});

function getItemlist()
{

	$.ajax({
		url : "itemlist_db",
		type : "POST",
		data : 
		{
			Lcategory: document.getElementById('Lcategory1').value,
			Mcategory: document.getElementById('Mcategory1').value,
			Scategory: document.getElementById('Scategory1').value
		},
		cache : false,
		async : false,
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(response) {	
			$('#itemlist').html("");
			
			if(response=='error')
			{
				alert("에러가 발생했습니다.");
			}
			else if(response=='Lcategory')
				alert('대분류를 입력해주세요.');
			else if(response=='Mcategory')
				alert('중분류를 입력해주세요.');
			else if(response=='Scategory')
				alert('소분류를 입력해주세요.');
			else
			{
				var list = response.itemlist;
				var listLen = list.length;
				
				for(var i=0;i<listLen;i++)
				{
					$('#itemlist').append("<div class='panel panel-default'><div class='panel-heading'><div class='row'>");
					$('#itemlist').append("<div class='col-xs-3 text-center'><i class='fa fa-comments fa-5x'></i></div>");
					$('#itemlist').append("<div class='col-xs-9'><div class='huge'><h4>"+list[i].iap+"</h4></div>");
					$('#itemlist').append("</h4></div><div>"+list[i].description+"</div></div></div></div>");
					$('#itemlist').append("<div><div class='panel-body'><span class='pull-left'>Users Purchased : "+"10,000"+"</span>");
					$('#itemlist').append("<span class='pull-right'>20 Coins</span>");
					$('#itemlist').append("<div class='clearfix'></div></div></div></div>");
				}
			}
		},
		
		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
			}
		}


	});
}

function addItem()
{
	
	$.ajax({
		url : "registerItem",
		type : "POST",
		data : 
		{
			ItemName: document.getElementById('ItemName').value,
			ItemDescription: document.getElementById('ItemDescription').value,
			GoogleID: document.getElementById('GoogleID').value,
			ItemPrice: document.getElementById('ItemPrice').value,
			Lcategory: document.getElementById('Lcategory2').value,
			Mcategory: document.getElementById('Mcategory2').value,
			Scategory: document.getElementById('Scategory2').value,
			Coin: document.getElementById('coinlist').value
		},
		cache : false,
		async : false,
		dataType : "text",

		success : function(response) {				
			if(response=='200')
			{
				alert("아이템이 정상적으로 등록되었습니다.");
				getItemlist();
			}
			else if(response=='error')
			{
				alert("에러가 발생했습니다.");
			}	
			else if(response=='Lcategory')
			{
				alert("대분류를 입력해주세요.");
			}	
			else if(response=='Mcategory')
			{
				alert("중분류를 입력해주세요.");
			}	
			else if(response=='Scategory')
			{
				alert("소분류를 입력해주세요.");
			}	
			else if(response=='ItemName')
			{
				alert("아이템이름을 입력해주세요.");
			}	
			else if(response=='ItemDescription')
			{
				alert("아이템설명을 입력해주세요.");
			}	
			else if(response=='ItemPrice')
			{
				alert("아이템 가격을 정상적으로 입력해주세요.");
			}	
			else if(response=='GoogleID')
			{
				alert("실제결제의 경우 구글 아이디를 입력해주세요.");
			}
			else if(response=='Coin')
			{
				alert("Coin을 선택해주세요.");
			}
			
			
		},
		
		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
			}
		}


	});
}

function getCoinlist()
{

	$.ajax({
		url : "coinlist_db",
		type : "POST",
		cache : false,
		async : false,
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(response) {	
			$('#coinlist').html("<option value='실제결제' selected>실제결제</option>");
			
			if(response=='error')
			{
				alert("에러가 발생했습니다.");
			}
			else
			{
				var mainlist = response.mainlist;
				var mainlistLen = mainlist.length;
				var sublist = response.sublist;
				var sublistLen = sublist.length;
				
				for(var i=0;i<mainlistLen;i++)
				{
					$('#coinlist').append("<option value='"+mainlist[i].name+"' >"+mainlist[i].name+"</option>");
				}
				for(var i=0;i<sublistLen;i++)
				{
					$('#coinlist').append("<option value='"+sublist[i].name+"' >"+sublist[i].name+"</option>");
				}
			}
		},
		
		error : function(request, status, error) {
			if (request.status != '0') {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText + "\r\nerror : " + error);
			}
		}


	});
}

</script>

<body id="page-top" class="index">
	<div class="container">
		<!-- ëí¼ -->
		<div id="wrapper">
			<!-- sidebar-wrapper -->
			<jsp:include page="projectnav.jsp" flush="false" />
			<!-- /#sidebar-wrapper -->

			<!-- page wrapper -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<!-- Item Categorization -->
					<div class="row">
						<div class="col-lg-12 text-center">
							<BR><BR><BR><BR><BR><BR>
							<h2>Item Management</h2>
							<BR>
						</div>
					</div>
					<div class = "row">
						<!-- 대분류 -->
						<div class="dropdown form-group col-lg-2">
							<select id="Lcategory1" name="Lcategory1" onchange="getMcategory('1')">
							</select>
							<select id="Mcategory1" name="Mcategory1" onchange="getScategory('1')">
								<option value='' selected>해당없음</option>
							</select>
							<select id="Scategory1" name="Scategory1" onchange="getItemlist()">
								<option value='' selected>해당없음</option>		
							</select>
							<div class="col-lg-2 pull-right">
								<button type="button" class="btn" data-toggle="modal" data-target="#AddItemModal" onclick="getCoinlist()">Add an Item</button>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div id='itemlist'></div>
					</div>
			<!-- /#page wrapper -->
		</div>
		<!-- /#ëí¼ -->
	</div>
	
	<!-- AddItemModal -->
	<div class="modal fade" id="AddItemModal" tabindex="-1" role="dialog" aria-labelledby="AddItemModalLabel">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Add an Item</h4>
		  </div>
		  <div class="modal-body">
			<div class="row">
				<div class="col-md-4 portfolio-item">
					<a href="#">
						<img class="img-responsive" src="http://placehold.it/700x400" alt="">
					</a>
					<p class="text-center">Item Image</p>
				</div>
				<div class="col-md-8 portfolio-item">
					<div class = "row">
						<div class="dropdown form-group col-md-4">
							<!-- 대분류 -->
							<div class="dropdown form-group col-lg-2">
								<select id="Lcategory2" name="Lcategory2" onchange="getMcategory('2')">
										</select>
							</div>
						</div>
						<div class="dropdown form-group col-md-4">
							<!-- 중분류 -->
							<div class="dropdown form-group col-lg-2">
								<select id="Mcategory2" name="Mcategory2" onchange="getScategory('2')">
									<option value='' selected>해당없음</option>
										</select>
							</div>
						</div>
						<div class="dropdown form-group col-md-4">
							<!-- 소분류 -->
							<div class="dropdown form-group col-lg-2">
								<select id="Scategory2" name="Scategory2">
									<option value='' selected>해당없음</option>		
										</select>
							</div>
						</div>
					</div>
					<div class="row">
						<label>Item Name</label>
						<input type="text" class="form-control" placeholder="Item Name" id="ItemName" required data-validation-required-message="Please enter Item Name.">
					</div>
					<div class="row">
						<label>Item Description</label>
						<input type="text" class="form-control" placeholder="Item Description" id="ItemDescription" required data-validation-required-message="Please enter Item Description.">
					</div>
					<div class="row">
						<label>Google ID</label>
						<input type="text" class="form-control" placeholder="Google ID" id="GoogleID" required data-validation-required-message="Please enter Google ID.">
					</div>
					<div class="row">
						<label>Item Price</label>
						<input type="text" class="form-control" placeholder="Item Price" id="ItemPrice" required data-validation-required-message="Please enter Item Price.">
					</div>
					<div class="row">
						<label>Coin to use</label>
						<div class="dropdown form-group">
							<select id="coinlist" name="coinlist">
								<option value='실제결제' selected>실제결제</option>		
							</select>
						</div>
					</div>
				</div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary" onclick="addItem()" data-dismiss="modal">Add</button>
		  </div>
		</div>
	  </div>
	</div>
</body>

</html>

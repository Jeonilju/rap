<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.rap.models.CategoryLInfo, com.rap.models.Virtual_MainInfo, com.rap.models.Virtual_SubInfo"%>

<!DOCTYPE html>
<html lang="en">
<!-- ìë¨ ë¤ë¹ê²ì´ì ë° ì¸í´ë£¨ë -->
<jsp:include page="nav.jsp" flush = "false" />
<script src="./resources/js/itemcategorization.js"></script>
<script type="text/javascript">

function getAllLcategory2()
{
	var id = ['#Lcategory1','#Lcategory2'];
	getLcategory(id);
}
function close()
{
	$('#AddItemModal').modal('hide');	
}

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
			
			if(response!=null){
				var list = response.itemlist;
				var listLen = list.length;
				var main = response.main;
				var sub = response.sub;
				var price='';
				
				for(var i=0;i<listLen;i++)
				{
					if(list[i].using_type == 1)
						price = list[i].price_main.toString() + main.name;
					else if(list[i].using_type == 2)
						price = list[i].price_sub.toString() + sub.name;
					else if(list[i].using_type == 3)
						price = list[i].price_real.toString() +'원';
					else
						price = '';
					$('#itemlist').append("<div class='panel panel-default'><div class='panel-heading'><div class='row'>");
					$('#itemlist').append("<div class='col-xs-3 text-center'><i class='fa fa-comments fa-5x'></i></div>");
					$('#itemlist').append("<div class='col-xs-9'><div class='huge'><h4>"+list[i].iap+"</h4></div>");
					$('#itemlist').append("<div>"+list[i].description+"</div></div></div></div>");
					$('#itemlist').append("<div class='row'><div class='panel-body'>");
					$('#itemlist').append("<span class='pull-right'>"+price+"</span>");
					$('#itemlist').append("<div class='clearfix'></div></div></div></div><div class='panel-footer'></div><br>");
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
				close();
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
			
			if(response!=null){
				var mainlist = response.mainlist;
				var mainlistLen = mainlist.length;
				var sublist = response.sublist;
				var sublistLen = sublist.length;
				
				for(var i=0;i<mainlistLen;i++)
				{
					$('#coinlist').append("<option value='"+mainlist[i].name+"' >"+mainlist[i].name+"</option>");
					$('#coinlist').selectpicker('refresh');

				}
				for(var i=0;i<sublistLen;i++)
				{
					$('#coinlist').append("<option value='"+sublist[i].name+"' >"+sublist[i].name+"</option>");
					$('#coinlist').selectpicker('refresh');

				}
				close();
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
							<select class="selectpicker" id="Lcategory1" name="Lcategory1" onchange="getMcategory('1')">
								<option value='' selected>대분류</option>
									<%
										List<CategoryLInfo> categoryLlist = (List<CategoryLInfo>)request.getAttribute("categoryLlist");
										int categoryLlistcount;
										if(categoryLlist == null) categoryLlistcount = 0;
										else categoryLlistcount = categoryLlist.size();
										
										for(int i =0;i<categoryLlistcount;i++)
										{
											out.println("<option value='"+categoryLlist.get(i).getCategoryL()+"'>"+categoryLlist.get(i).getCategoryL()+"</option>");
										}
									%>
							</select>
						</div>
						<div class="dropdown form-group col-lg-2">
						
							<select class="selectpicker"  id="Mcategory1" name="Mcategory1" onchange="getScategory('1')">
								<option value='' selected>중분류</option>
							</select>
							</div>
						<div class="dropdown form-group col-lg-2">
						
							<select class="selectpicker"  id="Scategory1" name="Scategory1" onchange="getItemlist()">
								<option value='' selected>소분류</option>		
							</select>
							</div>
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
						<div class="col-md-4" style="padding:0px; margin:0px;">
							<!-- 대분류 -->
								<select class="selectpicker"  id="Lcategory2" name="Lcategory2" onchange="getMcategory('2')">
									<option value='' selected>대분류</option>
									<%
										for(int i =0;i<categoryLlistcount;i++)
										{
											out.println("<option value='"+categoryLlist.get(i).getCategoryL()+"'>"+categoryLlist.get(i).getCategoryL()+"</option>");
										}
									%>
										</select>
						</div>
						<div class="col-md-4" style="padding:0px; margin:0px;">
							<!-- 중분류 -->
								<select class="selectpicker"  id="Mcategory2" name="Mcategory2" onchange="getScategory('2')">
									<option value='' selected>중분류</option>
								</select>
							
						</div>
						<div class="col-md-4" style="padding:0px; margin:0px;">
							<!-- 소분류 -->
							<select class="selectpicker"  id="Scategory2" name="Scategory2">
									<option value='' selected>소분류</option>		
							</select>
							
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
							<select class="selectpicker"  id="coinlist" name="coinlist">
								<option value='실제결제' selected>실제결제</option>		
							</select>
						</div>
					</div>
				</div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary" onclick="addItem()">Add</button>
		  </div>
		</div>
	  </div>
	</div>
</body>

</html>

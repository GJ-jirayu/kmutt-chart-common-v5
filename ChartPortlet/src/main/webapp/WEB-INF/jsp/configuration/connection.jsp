<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connection</title>
<c:set var="ns"><portlet:namespace/></c:set>
<portlet:resourceURL var="getConnection" id="getConnection"></portlet:resourceURL>  
<portlet:actionURL var="formActionSave">
        <portlet:param name="action" value="doSave"/>
</portlet:actionURL>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/fusioncharts.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<style type="text/css">
	.errorMessage{ color:red;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var controlMessage = "${connectionForm.message}";
		if(controlMessage.length>1){
			alert(controlMessage);
		}
	});
	function connNew(){
		$("#web_${ns} #mode").val("add");
		var result = confirm("Do you want to save as this connection ?");
		if (!result) {
		    return false;
		}
		connFormSubmit();
	}
	function connUpdate(){
		$("#web_${ns} #mode").val("edit");
		var result = confirm("Do you want to update this connection ?");
		if (!result) {
		    return false;
		}
		connFormSubmit();
	}
	function connDelete(){
		$("#web_${ns} #mode").val("delete");
		var result = confirm("Do you want to delete this connection ");
		if (!result) {
		    return false;
		}
		connFormSubmit();
	}
	function connFormSubmit(){
		$("#web_${ns} #submitMessage").html(""); //reset
		var invalid = [];
		$("#web_${ns} .detail").each(function(){
			if(this.value == ""){
				invalid.push( $(this).previous("label").html());
			}	
		});
		if(invalid.length<=0){
			$("#connectionForm").submit();
		}else{
			var message = "Require following fields: ["+invalid.join(",")+"]";
			$("#web_${ns} #submitMessage").html(message);
		}
	}
	function recallConnection(select){
			$("#web_${ns} .detail").val("");
			$.ajax({
	   	 		dataType: "json",
	   	 		url:"<%=getConnection%>",
	   	 		data: { connId: select.value },
	   	 		success:function(data){
	   	 			console.log(data);
	   	 			if(data["header"]["success"]>0){
	   	 				renderConnDetail(data["content"]);
	   	 			}
	   	 			else{
	   	 				$("#web_${ns} #sqlMessage").html("retrive connection detail not complete");
	   	 			}
	   	 		},fail:function(data){
	   	 			$("#web_${ns} #sqlMessage").html("retrive connection detail error");
	   	 		} 
	   	 	});
	}
	function renderConnDetail(data){
		$("#web_${ns} #connName").val(data["connName"]);
		$("#web_${ns} #connString").val(data["connString"]);
		$("#web_${ns} #driverClass").val(data["driverClass"]);
		$("#web_${ns} #dialect").val(data["dialect"]);
		$("#web_${ns} #username").val(data["username"]);
		$("#web_${ns} #password").val(data["password"]);
	}
</script>
</head>
<body>
	<div id="web_${ns}">
	<form:form id="connectionForm" modelAttribute="connectionForm" method="post" name="connectionForm" action="${formActionSave}" enctype="multipart/form-data">
		<form:hidden id="mode" path="mode" />
		<label>Connection List</label>
		<form:select id="connId" path="model.connId"  onchange="recallConnection(this)" style="width:320px;" >
			<form:option value="" label=""/>
   			<form:options items="${connectionForm.conns}" itemValue="connId" itemLabel="connName"/>
		</form:select>
		<br/>
		<label>Connection</label>
		<label>Name</label><form:input id="connName" class="detail" type="text" path="model.connName" style="width:320px;" /><br/>
		<label>Connect string</label><form:input id="connString" type="text" class="detail" path="model.connString" style="width:320px;" /><br/>
		<label>Driver class</label><form:input id="driverClass" type="text" class="detail" path="model.driverClass" style="width:320px;" /><br/>
		<label>Hibernate Dialect</label><form:input id="dialect" type="text" class="detail" path="model.dialect" style="width:320px;" /><br/>
		<label>user</label><form:input id="username" type="text" class="detail" path="model.username" style="width:320px;" /><br/>
		<label>password</label><form:input id="password" type="password" class="detail" path="model.password" style="width:320px;"/><br/>
		<p id="submitMessage"></p>
		<input type="button" class="btn btn-primary" onclick="connNew()" value="Save as"/>
		<input type="button" class="btn btn-primary" onclick="connUpdate()" value="Update"/>
		<input type="button" class="btn btn-primary" onclick="connDelete()" value="Delete"/> 		
	</form:form>
	</div>
</body>
</html>
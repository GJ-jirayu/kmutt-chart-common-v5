<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chart Datasource</title>
<c:set var="ns"><portlet:namespace/></c:set>
<portlet:actionURL var="formAction">
        <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
<portlet:actionURL var="formActionNew">
        <portlet:param name="action" value="doNew"/>
</portlet:actionURL>
<portlet:actionURL var="formActionUpdate">
        <portlet:param name="action" value="doUpdate"/>
</portlet:actionURL>
<portlet:actionURL var="formActionDelete">
        <portlet:param name="action" value="doDelete"/>
</portlet:actionURL>
<portlet:resourceURL var="selectedDatasource" id="selectedDatasource" ></portlet:resourceURL>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/fusioncharts.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<style type="text/css">
	#web_${ns} .chart_ds_content label{ color:red; }
	#web_${ns} .chart_ds_name select{ }
	#web_${ns} .chart_ds_param select{ height:250px;width:200px;overflow-x:scroll}
	#web_${ns} .userAuthority select{ height:250px;width:200px;overflow-x:scroll}
	#web_${ns} .chart_param select{ height:250px;width:200px;overflow-x:scroll}
	.selection_div>div{ display:inline-block;}
	.errorMessage{ color:red;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var controlMessage = "${chartDatasourceForm.message}";
		if(controlMessage.length>1){
			alert(controlMessage);
		}
	});
	function selectedChart(current){
		var selectedElement=$("#web_${ns} select#chartInitList option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
			 $(this).hide();
			 $(this).prop("selected",false);
		});
		var renderSelectedElement=$("#web_${ns} select#chartUsedList");
		for(var i=0;i<selected.length;i++){
			var newOpt = $("<option/>");
			newOpt.html( selected[i][1] );
			newOpt.attr("value",selected[i][0]);
			renderSelectedElement.append(newOpt);
		}//end for
	}
	function deselectedChart(current){
		var selectedElement=$("#web_${ns} select#chartUsedList option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
		     $(this).remove();
		});
		for(var i=0;i<selected.length;i++){
			var inx = selected[i][0];
			var opt = $("#web_${ns}  select#chartInitList option[value='"+inx+"']");
			opt.show();
			opt.prop("selected",true);
		}//end for
	}
	// control action
	function selectedFilter(current){
		var selectedElement=$("#web_${ns} .chart_ds_param .chart_ds_param_avaliable select option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
			 $(this).hide();
			 $(this).prop("selected",false);
		});
		var renderSelectedElement=$("#web_${ns} .chart_ds_param .chart_ds_param_used select");
		for(var i=0;i<selected.length;i++){
			var newOpt = $("<option/>");
			newOpt.html( selected[i][1] );
			newOpt.attr("value",selected[i][0]);
			renderSelectedElement.append(newOpt);
		}//end for
	}
	function deselectedFilter(current){
		var selectedElement=$("#web_${ns} .chart_ds_param .chart_ds_param_used select option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
		     $(this).remove();
		});
		for(var i=0;i<selected.length;i++){
			var inx = selected[i][0];
			var opt = $("#web_${ns} .chart_ds_param .chart_ds_param_avaliable select option[value='"+inx+"']");
			opt.show();
			opt.prop("selected",true);
		}//end for
	}
	
	// for user selection
	function selectedUser(current){
		//var selectedElement=$("#web_${ns} .chart_param .chart_param_avaliable select option:selected");
		var selectedElement=$("#web_${ns} select#userInitList option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
			 $(this).hide();
			 $(this).prop("selected",false);
		});
		var renderSelectedElement=$("#web_${ns} select#userUsedList");
		for(var i=0;i<selected.length;i++){
			var newOpt = $("<option/>");
			newOpt.html( selected[i][1] );
			newOpt.attr("value",selected[i][0]);
			renderSelectedElement.append(newOpt);
		}//end for
	}
	function deselectedUser(current){
		var selectedElement=$("#web_${ns} select#userUsedList option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
		     $(this).remove();
		});
		for(var i=0;i<selected.length;i++){
			var inx = selected[i][0];
			var opt = $("#web_${ns} select#userInitList option[value='"+inx+"']");
			opt.show();
			opt.prop("selected",true);
		}//end for
	}
	//page function
	function formSubmit(actionName){
		var id = $("web_${ns} select#datasourceId").val();
		var text = $("web_${ns} select#datasourceId").find('option:selected').text();
		if(id==0){
			return false;
		}
		var actionMessage = "";
		if(actionName=="new"){
			actionMessage = "Do you want to save as this service ?";
		}else if(actionName=="update"){
			actionMessage = "Do you want to update service ?";
		}else if(actionName=="delete"){
			actionMessage = "Do you want to delete service ?";
		}else{
			actionMessage = actionName+"?";
		}
		var result = confirm(actionMessage);
		if (!result) {
		    return false;
		}
		if(actionName=="new"){
			$("#web_${ns} form#chartDatasource").attr("action","<%=formActionNew%>");
		}else if(actionName=="update"){
			$("#web_${ns} form#chartDatasource").attr("action","<%=formActionUpdate%>");
		}else if(actionName=="delete"){
			$("#web_${ns} form#chartDatasource").attr("action","<%=formActionDelete%>");
		}else{
			return false;
		}
		//reset message
		$("#web_${ns} #sqlMessage").html(""); 
		var errorMessage = checkValidSqlFilter();
		if(errorMessage==null){
			var selectedFilter=$("#web_${ns} .chart_ds_param .chart_ds_param_used select option");
			selectedFilter.each(function(){
				$(this).prop("selected",true);
			});
			//chart
			$("#web_${ns} select#chartUsedList option").each(function(){
				$(this).prop("selected",true);
			});
			//user
			$("#web_${ns} select#userUsedList option").each(function(){
				$(this).prop("selected",true);
			});
			// do submit case
			$("#web_${ns} form#chartDatasource").submit();
		}else{
			$("#web_${ns} #sqlMessage").html(errorMessage);
		}
	}
	function checkValidSqlFilter(){
		var message = null;
		var invalidParam = [];
		var sql = $("#web_${ns} #sqlString").val();
		var usedParamName = [];
		var selectedFilter=$("#web_${ns} .chart_ds_param .chart_ds_param_used select option");
		selectedFilter.each(function(){
			usedParamName.push($(this).html());  //  display text here
		}); // end get ParamName from list
		for(var i=0;i<usedParamName.length;i++){
			var rx = new RegExp(':'+usedParamName[i]+'\\s');
			if( sql.search(rx) > -1 ){}
			else{ invalidParam.push(usedParamName[i]);	}
		}//end loop
		if(invalidParam.length>0)
			return "Param: ["+invalidParam.join(",")+"] not exists";
		else return null;
	}
	function setDatasource(current){
		requestDatasourceDetail(current.value);
	}
	function requestDatasourceDetail(selectId){
		$("#web_${ns} #sqlMessage").html("waiting");
		var datasourceId = selectId;
		$.ajax({
   	 		dataType: "json",
   	 		url:"<%=selectedDatasource%>",
   	 		data: { datasourceId: datasourceId },
   	 		success:function(data){
   	 				$("#web_${ns} #sqlMessage").empty();
   	 				
   	 				renderDsDetail(data["content"]["datasource"]);
   	 				renderFilter(data["content"]["filter"]);
   	 				renderChart(data["content"]["chart"]);
   	 				renderUser(data["content"]["user"]);
   	 		},error:function(data){
				$("#web_${ns} #sqlMessage").html("Datasource detail error");
   	 		} 
   	 	});
	}
	function renderDsDetail(obj){
		$("#web_${ns} #datasourceName").val(obj["sname"]);
		$("#web_${ns} #sqlString").val(obj["sql"]);
		$("#web_${ns} #selectedConnId").val(obj["conn"]);
	}
	function renderFilter(obj){
		
		$("#web_${ns} select#filterInitList option").each(function(){
			$(this).prop("selected",false);
			$(this).show();
		});
		$("#web_${ns} select#filterUsedList").empty();
		
		for(var i=0;i<obj["fusedlist"].length;i++){
			var opt =  $("<option />");
			var value = obj["fusedlist"][i]["fid"];
			opt.attr("value",value);
			opt.html(obj["fusedlist"][i]["fname"]);
			$("#web_${ns} select#filterUsedList").append(opt);
			//disable
			var target = $("#web_${ns} select#filterInitList").find("option[value="+value+"]");
			target.prop("selected",false);
			target.hide();
		}
	}
	function renderChart(obj){
		$("#web_${ns} select#chartInitList option").each(function(){
			$(this).prop("selected",false);
			$(this).show();
		});
		$("#web_${ns} select#chartUsedList").empty();
		
		for(var i=0;i<obj["cusedlist"].length;i++){
			var opt =  $("<option />");
			var value = obj["cusedlist"][i]["cid"];
			opt.attr("value",value);
			opt.html(obj["cusedlist"][i]["cname"]);
			$("#web_${ns} select#chartUsedList").append(opt);
			//disable
			var target = $("#web_${ns} select#chartInitList").find("option[value="+value+"]");
			target.prop("selected",false);
			target.hide();
		}
	}
	function renderUser(obj){
		$("#web_${ns} select#userInitList option").each(function(){
			$(this).prop("selected",false);
			$(this).show();
		});
		$("#web_${ns} select#userUsedList").empty();
		
		for(var i=0;i<obj["userUsedlist"].length;i++){
			var opt =  $("<option />");
			var value = obj["userUsedlist"][i]["uid"];
			opt.attr("value",value);
			opt.html(obj["userUsedlist"][i]["uname"]);
			$("#web_${ns} select#userUsedList").append(opt);
			//disable
			var target = $("#web_${ns} select#userInitList").find("option[value="+value+"]");
			target.prop("selected",false);
			target.hide();
		}
	}
</script>
</head>
<body>
	<div id="web_${ns}" class="chart_ds_content">
	<form:form id="chartDatasource" modelAttribute="chartDatasourceForm" method="post" name="chartDatasourceForm" action="${formAction}" enctype="multipart/form-data">
		
		<form:hidden id="mode" path="mode"/>
		<div class="chart_ds_name">
			<label>Datasource List</label>
			<form:select style="min-width:70%" id="datasourceId" path="datasourceId" onchange="setDatasource(this)">
				<form:option value="0" label=""/>
				<form:options items="${chartDatasourceForm.datasources}" itemValue="serviceId" itemLabel="serviceName" />
			</form:select> 
			<br/>
			<label>Datasource Name</label>
			<form:input id="datasourceName" style="min-width:70%" path="datasourceName"/>
		</div>
		<div class="chart_ds_data">
			<label>Datasource Value</label>
			<label>Connection</label>
			<form:select id="selectedConnId" path="selectedConnId" items="${chartDatasourceForm.connections}" itemValue="connId" itemLabel="connName" multiple="false"></form:select>
			<label>Sql Query</label>
			<form:textarea id="sqlString"  style="min-width:80%;max-width:90%;" path="sqlString" rows="10" />
			<p id="sqlMessage" class="errorMessage"></p>
		</div>
		<div> <!-- test -->
			<div class="chart_ds_param selection_div">
				<div class="chart_ds_param_avaliable">
					<form:select id="filterInitList" path="initialFilterList" items="${chartDatasourceForm.filterList}" itemValue="filterId" itemLabel="filterName" multiple="true"/>
				</div>
				<div class="chart_ds_param_control">
					<input type="button" onclick="selectedFilter(this)" value=">>"/>
					<br/>
					<input type="button" onclick="deselectedFilter(this)" value="<<"/>
				</div>
				<div class="chart_ds_param_used">
					<form:select id="filterUsedList" path="filterUsedList" multiple="true">
						<form:options items="${chartDatasourceForm.filterUsedList}" itemValue="filterId" itemLabel="filterName"/>
					</form:select>
				</div>
			</div>
			<span>Chart Authority</span>
			<div class="chart_param selection_div">
				<div class="chart_param_avaliable">
					<form:select id="chartInitList" path="chartInitList" items="${chartDatasourceForm.chartList}" itemValue="chartId" itemLabel="chartName" multiple="true"/>
				</div>
				<div class="chart_param_control">
					<input type="button" onclick="selectedChart(this)" value=">>"/>
					<br/>
					<input type="button" onclick="deselectedChart(this)" value="<<"/>
				</div>
				<div class="chart_param_used">
					<form:select id="chartUsedList" path="chartUsedList" multiple="true">
						<form:options items="${chartDatasourceForm.chartUsedList}" itemValue="chartId" itemLabel="chartName"/>
					</form:select>
				</div>
			</div>
		</div>
		<span>User Authority</span>
		<div class="userAuthority selection_div">
			<div class="user_avaliable">
				<form:select id="userInitList" path="userInitList" items="${chartDatasourceForm.userList}" itemValue="userId" itemLabel="username" multiple="true"/>
			</div>
			<div class="user_control">
				<input type="button" onclick="selectedUser(this)" value=">>"/>
				<br/>
				<input type="button" onclick="deselectedUser(this)" value="<<"/>
			</div>
			<div class="user_used">
				<form:select id="userUsedList" path="userUsedList" multiple="true">
					<form:options items="${chartDatasourceForm.userUsedList}" itemValue="userId" itemLabel="username"/>
				</form:select>
			</div>
		</div>
		
		<div class="chart_ds_foolter" style="text-align:center;">
			<br/>
			<input type="button"  class="btn btn-primary"  onclick="formSubmit('new')" value="Save as"/>
			&nbsp;
			<input type="button"  class="btn btn-primary"  onclick="formSubmit('update')" value="Update"/>
			&nbsp;
			<input type="button"  class="btn btn-primary"  onclick="formSubmit('delete')" value="delete"/>
			<br/>
		</div>
	</form:form>
	</div>
</body>
</html>
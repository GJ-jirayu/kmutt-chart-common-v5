<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filter</title>
<c:set var="ns"><portlet:namespace/></c:set>
<portlet:resourceURL var="selectedFilter" id="selectedFilter"></portlet:resourceURL>  
<portlet:actionURL var="formActionSubmit">
        <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/fusioncharts.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<style type="text/css">
	#web_${ns} .filter_content label{ color:red; }
	#web_${ns} .filter_name select{ }
	#web_${ns} .filter_param select{ height:250px;width:200px;overflow-x:scroll}
	.filter_param>div{ display:inline-block;}
	.errorMessage{ color:red;}
	#filterF label{ display:inline-block; min-width:100px; vertical-align:top;	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var controlMessage = "${filterForm.message}";
		if(controlMessage.length>1){
			alert(controlMessage);
		}
	});
	function getSelectValues(select) {
	  var result = [];
	  var options = select && select.options;
	  var opt;
	  for (var i=0, iLen=options.length; i<iLen; i++) {
	    opt = options[i];
	    if (opt.selected) {
	      result.push(opt.value || opt.text);
	    }
	  }
	  return result;
	}
	function doSelectParam(){
		var main = $("#web_${ns}>chart_ds_name>select");
		getSelectValues(main.get(0));
	}
	// control action
	function setName(current){
		requestFilterDetail(current.value);
	}
	function requestFilterDetail(filterId){
		$.ajax({
   	 		dataType: "json",
   	 		url:"<%=selectedFilter%>",
   	 		data: { filterId: filterId },
   	 		success:function(data){
   	 			console.log(data);
   	 			if(data["header"]["success"]>0){
   	 				//resetStateFilterList();
   	 				renderFilterDetail(data["content"]);
   	 			}
   	 			else{
   	 				$("#web_${ns} #sqlMessage").html("retrive filter detail error");
   	 			}
   	 		} 
   	 	});
	}
	function renderFilterDetail(data){
		//param_used
		var p_use_cnt = $("#web_${ns} .filter_param .filter_param_used select");
		p_use_cnt.empty();  //reset
		
		$("#web_${ns} #filterId").val(data.fid);
		
		$("#web_${ns} #filterName").val(data.fname);
		$("#web_${ns} #sqlString").val(data.sql);  
		$("#web_${ns} #type").val(data.valueType);
		var g =  data.global==1 ? true : false;
		$("#web_${ns} #global").prop("checked",g);
		var s = data.system==1? true : false;
		$("#web_${ns} #system").prop("checked",s);
		var auv = data.auto==1? true : false;
		$("#web_${ns} #autoFill").prop("checked",auv);
		
		$("#web_${ns} #defaultValue").val(data.defaultValue);
		$("#web_${ns} #label").val(data.label);
		$("#web_${ns} #connId").val(data.connId);
		// disable list by have used
		for(var i=0;i< data.fusedlist.length ; i++){
			var idx = data.fusedlist[i].fid;
			var opt = $("#web_${ns} .filter_param .filter_param_avaliable select option[value='"+idx+"']");
			opt.hide();
			var newopt = $("<option/>");
			newopt.attr("value",data.fusedlist[i].fid);
			newopt.html(data.fusedlist[i].fname);
			p_use_cnt.append(newopt);
		};
		
	}
	function selectedFilter(current){
		var selectedElement=$("#web_${ns} .filter_param .filter_param_avaliable select option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
			 $(this).hide();
			 $(this).prop("selected",false);
		});
		var renderSelectedElement=$("#web_${ns} .filter_param .filter_param_used select");
		for(var i=0;i<selected.length;i++){
			var newOpt = $("<option/>");
			newOpt.html( selected[i][1] );
			newOpt.attr("value",selected[i][0]);
			renderSelectedElement.append(newOpt);
		}//end for
	}
	function deselectedFilter(current){
		var selectedElement=$("#web_${ns} .filter_param .filter_param_used select option:selected");
		var selected = [];
		selectedElement.each(function(){
			 selected.push(  [$(this).val(),$(this).html()]  );
		     $(this).remove();
		});
		for(var i=0;i<selected.length;i++){
			var inx = selected[i][0];
			var opt = $("#web_${ns} .filter_param .filter_param_avaliable select option[value='"+inx+"']");
			opt.show();
			opt.prop("selected",true);
		}//end for
	}
	function checkValidSqlFilter(){
		var message = null;
		var invalidParam = [];
		var sql = $("#web_${ns} #sqlString").val();
		var usedParamName = [];
		var selectedFilter=$("#web_${ns} .filter_param .filter_param_used select option");
		selectedFilter.each(function(){
			usedParamName.push(this.innerHTML);
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
	function filterNewData(){
		
		var result = confirm("Do you want to save as this service ?");
		if (!result) {
		    return false;
		}
		
		//set id none
		$("#web_${ns} #filterId").val("");
		$("#web_${ns} #mode").val("add");
		
		$("#web_${ns} #sqlMessage").html(""); 
		var errorMessage = checkValidSqlFilter();
		if(errorMessage==null){
			var selectedFilter=$("#web_${ns} .filter_param .filter_param_used select option");
			selectedFilter.each(function(){
				$(this).prop("selected",true);
			});
			$("#web_${ns} form#filterF").submit();
		}else{
			alert("Selected none");
		}
	}
	function filterEditData(){
		var result = confirm("Do you want to update service ?");
		if (!result) {
		    return false;
		}
		if($("#web_${ns} #filterId").val()!=null && $("#web_${ns} #filterId").val()!="none" ){
			$("#web_${ns} #mode").val("edit");
			$("#web_${ns} #sqlMessage").html(""); 
			var errorMessage = checkValidSqlFilter();
			if(errorMessage==null){
				var selectedFilter=$("#web_${ns} .filter_param .filter_param_used select option");
				selectedFilter.each(function(){
					$(this).prop("selected",true);
				});
				$("#web_${ns} form#filterF").submit();
			}else{
				$("#web_${ns} #sqlMessage").html(errorMessage);
			}
		}else{
			alert("Selected none");
		}
	}
	function filterDelete(){
		var result = confirm("Do you want to delete service ?");
		if (!result) {
		    return false;
		}
		if($("#web_${ns} #filterId").val()!=null && $("#web_${ns} #filterId").val()!="none" ){
			$("#web_${ns} #mode").val("delete");
			$("#web_${ns} #sqlMessage").html(""); 
			var errorMessage = checkValidSqlFilter();
			if(errorMessage==null){
				$("#web_${ns} form#filterF").submit();
			}else{
				$("#web_${ns} #sqlMessage").html(errorMessage);
			}
		}else{
			alert("Selected none");
		}
	}
</script>
</head>
<body>
	<div id="web_${ns}" class="filter_content">
	<form:form id="filterF" modelAttribute="filterForm" method="post" name="filterForm" action="${formActionSubmit}" enctype="multipart/form-data">
		<!--<c:if test="${not empty filterForm.message }">
		<span id="formMessage" class="errorMessage">${filterForm.message}</span>
		</c:if>-->
		<form:hidden id="mode" path="mode" />
		<div class="filter_name">
			<label>FilterList</label>
			<form:select style="min-width:45%" id="filterId" path="filterId" items="${filterForm.filters}" itemValue="filterId" itemLabel="filterName" onchange="setName(this)" />
			<br/>
			<label>FilterName</label>
			<form:input style="width:22%" id="filterName" path="filterName"/>
			<br/>
			<label>Label</label>
			<form:input style="width:22%" id="label" path="label"/>
		</div>
		<div class="filter_properties" style="padding-bottom:15px;">
			<label>Input Type</label><form:select path="type" items="${filterForm.types}" style="160px;margin-right:80px;"></form:select>
			<label>Default </label> <form:input style="width:120px" id="defaultValue" path="defaultValue"/>
			<br/><label>Advance</label>
			&nbsp;<form:checkbox id="global" path="global" />&nbsp;global&nbsp;
			&nbsp;<form:checkbox id="system" path="system" />&nbsp;system&nbsp;
			&nbsp;<form:checkbox id="autoFill" path="autoFill" />&nbsp;auto fill&nbsp; 
		</div>
		<div class="filter_data">
			<label>Data</label>
			<br/>
			<label>Connection</label>
			<form:select id="connId" path="selectedConnection" items="${filterForm.connections}" itemValue="connId" itemLabel="connName" style="160px;"  multiple="false"></form:select>
			<br/><label>Sql Query</label>
			<form:textarea id="sqlString"  style="min-width:45%;vertical-align:text-top;" path="sqlString" rows="5" />
			<p id="sqlMessage" class="errorMessage"></p>
		</div>
		<div class="filter_param">
			<label>Parameter</label>
			<div class="filter_param_avaliable">
				<form:select path="initialFilterList" items="${filterForm.filterList}" itemValue="filterId" itemLabel="filterName" multiple="true"/>
			</div>
			<div class="filter_param_control">
				<input type="button" onclick="selectedFilter(this)" value="Select"/>
				<br/>
				<input type="button" onclick="deselectedFilter(this)" value="Deselect"/>
			</div>
			<div class="filter_param_used">
				<form:select id="filterUsedList" path="filterUsedList" multiple="true">
					<form:options items="${filterForm.filterUsedList}" itemValue="filterId" itemLabel="filterName"/>
				</form:select>
			</div>
		</div>
		
		
		<div class="filter_footer">
			<br/>
			<p id="submitMessage"></p>
			<input type="button"  class="btn btn-primary" style="position:relative;left:20%;" onclick="filterNewData()" value="Save as"/>
			<input type="button"  class="btn btn-primary" style="position:relative;left:20%;" onclick="filterEditData()" value="Update"/>
			<input type="button"  class="btn btn-primary" style="position:relative;left:20%;" onclick="filterDelete()" value="Delete"/>
			<br/>
		</div>
	</form:form>
	</div>
</body>
</html>
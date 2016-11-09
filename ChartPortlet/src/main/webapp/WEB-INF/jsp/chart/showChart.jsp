<%--
  Created by IntelliJ IDEA. (imake)
  Date: 07/09/2015
  Time: 19:10
  To change this template use File | Settings | File Templates.
  REVESION BY GJ.PK.m  
  Date: 2016-03
--%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<c:set var="ns"><portlet:namespace/></c:set>
<portlet:resourceURL var="cascadeInternalFilter" id="cascadeInternalFilter"></portlet:resourceURL>  
<portlet:resourceURL var="buildChartAjax" id="buildChartAjax"></portlet:resourceURL>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title></title>
    <style type="text/css">
	    .aui .table td{
	    	background-color:transparent;
	    }
    	.aui .table thead th {
	    	background-color:transparent;
	    }
	    .aui select{
	    	width:auto !important;
	    	max-width:98%;
	    }
	    .chartContainer .aui .table thead th {
	    	background-color:transparent;
	    }
	    .chartContainer .aui .table{
	    	background-color:transparent;
	    }
	    .chartContainer .aui .table tbody{
	    	background-color:transparent;
	    }
	     .chartContainer .aui .table tbody td {
	     	background-color:transparent;
	     }
	      .chartContainer .aui .table thead{
	      background-color:transparent;
	      }
	     .chartContainer .aui .table thead td {
	     	background-color:transparent;
	     }
	     .minChartContainer{ min-height:220px;}
    </style>
    <!-- Bootstrap core CSS -->
    <%--
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
--%>

</head>
<!-- class="icon-comment icon-white"  -->
<%--
<div><i id="${ns}myTooltip"  class="icon-comment icon-white"  title="${chartSettingForm.comment}"
        style="position:relative;top:0;left:97%;cursor: pointer;background-image:
                url('<c:url value="/resources/images/comment.jpg"/>') ">

</i> </div>
--%>
<div>
    <%--
    <button id="${ns}comment_bt" type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Comment"
            data-content="${chartSettingForm.comment}">Click to toggle popover</button>
--%>
        <a tabindex="0" id="${ns}comment_bt"
            data-toggle="popover" data-trigger="focus" title="Comment"
           data-content="${chartSettingForm.comment}">
            <img id="${ns}linktox" src="<c:url value="/resources/images/comment.jpg"/>" style="cursor:pointer;width:16px;height: 16px;padding-left:5px" />
        </a>
        <%--
        <img id="${ns}comment_bt"  data-toggle="popover" data-trigger="focus" title="Comment"
             data-content="${chartSettingForm.comment}" src="<c:url value="/resources/images/comment.jpg"/>"
        style="position:relative;top:0;left:0%;cursor:pointer;width:16px;height: 16px"  />
    <img id="${ns}myTooltip"   src="<c:url value="/resources/images/comment.jpg"/>"
          style="position:relative;top:0;left:0%;cursor:pointer;width:16px;height: 16px" title="${chartSettingForm.comment}" />
          --%>
    <c:if test="${not empty chartSettingForm.linkTo}">
        <%--  onclick="${ns}linkto('${chartSettingForm.linkTo}')"
        onclick="window.open('${chartSettingForm.linkTo}');" target="_blank"
        --%>
        <a  onclick='${ns}linkto("${chartSettingForm.linkTo}")'  >
        <img id="${ns}linktox" src="<c:url value="/resources/images/link-xxl.png"/>" style="cursor:pointer;width:16px;height: 16px;padding-left:5px" />
            </a>
    </c:if>
	<img id="${chartSettingForm.chartInstance}_cascadeWaiting"  src="<c:url value="/resources/images/rotate.gif"/>" style="cursor:pointer;width:22px;height: 22px;padding-left:5px;display:none;" />
</div>
<c:if test="${chartSettingForm.dataSourceType=='1' && chartSettingForm.showFilter=='1' }">
    <portlet:actionURL var="formAction">
        <portlet:param name="action" value="doSubmit"/>
    </portlet:actionURL>
    <form:form id="${ns}_chartSettingForm" modelAttribute="chartSettingForm" method="post" name="chartSettingForm"
               action="${formAction}" enctype="multipart/form-data">
        <form:hidden path="dataSourceType"/>
        <form:hidden id="chartInstance" path="chartInstance"/>
        <form:hidden path="globalFilterString"/>
		  <c:if test="${not empty filters}">
	        <c:forEach items="${filters}" var="filter" varStatus="loop">
	        	<div style="display:inline-block">
	            	&nbsp;&nbsp; ${filter.title}   &nbsp;&nbsp;
	           		<c:choose>
	           			<c:when test="${filter.valueType.equals('manual input') }">
	           				<input type="text" id="i_filter_${filter.filterId}" name="i_filter_${filter.filterId}" data-filter="${filter.filterName}" class="internal_filter" value="${filter.selectedValue}" onchange="F${chartSettingForm.chartInstance}_cascade(this)" />
	           			</c:when>
	           			<c:when test="${filter.valueType.equals('select')}">
			                <select id="i_filter_${filter.filterId}" name="i_filter_${filter.filterId}" class="internal_filter" data-filter="${filter.filterName}" onchange="F${chartSettingForm.chartInstance}_cascade(this)">
			                       	<c:forEach items="${filter.filterValues}" var="filterValue" varStatus="loop2">
			                            <c:choose>
										    <c:when test="${filter.selectedValue.equals(filterValue.keyMapping)}">
										        <option value="${filterValue.keyMapping}" selected>${filterValue.valueMapping}</option>
										    </c:when>    
										    <c:otherwise>
										            <option value="${filterValue.keyMapping}">${filterValue.valueMapping}</option>
										    </c:otherwise>
										</c:choose>
			                        </c:forEach>
			                </select>
			            </c:when>
			            <c:when test="${filter.valueType.equals('multiple select')}">
			            	<select id="i_filter_${filter.filterId}" name="i_filter_${filter.filterId}" class="internal_filter" data-filter="${filter.filterName}" multiple onchange="F${chartSettingForm.chartInstance}_cascade(this)">
			                       	<c:forEach items="${filter.filterValues}" var="filterValue" varStatus="loop2">
			                            <c:choose>
										    <c:when test="${filter.selectedValue.equals(filterValue.keyMapping)}">
										        <option value="${filterValue.keyMapping}" selected>${filterValue.valueMapping}</option>
										    </c:when>    
										    <c:otherwise>
										            <option value="${filterValue.keyMapping}">${filterValue.valueMapping}</option>
										    </c:otherwise>
										</c:choose>
			                        </c:forEach>
			                </select>
			            </c:when>
			    	</c:choose>
	            </div>
	        </c:forEach>
	    </c:if>
    	<div style="display:inline-block">
	    	<input type="button"  class="btn btn-primary" onclick="F${ns}_submitAjax()" value="submit"/>
	    </div>
    </form:form>
</c:if>
<table border="0" width="100%">
<div align="center" id="${ns}chart_table_caption"></div>
<div align="center" id="${ns}chart_table_subCaption"></div>
</table>
<div id="${ns}chartContainer" class="chartContainer"> Please Config Chart!</div>
<%--
<span id="${ns}chartContainer" style="line-height: 100%; display:
inline-block; zoom: 1; width: 99%; height: 34.65px; background-color: rgb(255, 255, 255);">
Please Config Chart!
</span>
--%>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/fusioncharts.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/wtpTable.js"/>"></script>


<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<%--
<script src="<c:url value='/resources/js/bootbox.min.js'/>" type="text/javascript"></script>
--%>
<script type="text/javascript">
    function ${ns}linkto(link_url){
        window.open(link_url,"_blank");
    }
    $(document).ready(function () {
        $("#${ns}comment_bt").popover({
            html:true
        });
        $("#${ns}comment_bt2").popover({
            html:true
        });
        var dataSource;
        var chartype="${chartSettingForm.chartType}";
        var chartHeight="300"; //default
        if(chartype=="gantt"){ chartHeight="600"; }
        else if(chartype=="hbullet"){ chartHeight="150"; }
        <c:if test="${not empty chartSettingForm.jsonStr && chartSettingForm.chartType!='table'}">
        var revenueChart = new FusionCharts({
            "type": "${chartSettingForm.chartType}",
            "renderAt": "${ns}chartContainer",
            "width": "100%", // 500
            "height": "${chartSettingForm.chartHeight}", // chartHeight,
            "dataFormat": "json",
            "dataSource":${chartSettingForm.jsonStr}
        });
        revenueChart.render();
        </c:if>        
        <c:if test="${chartSettingForm.chartType=='table' }">
			   	var jsonStrObj=${chartSettingForm.jsonStr};
				var table1 = new wtpTable("#${ns}chartContainer",jsonStrObj);
				table1.updatePath("/ChartPortlet");
				table1.render();
        </c:if>
        /*check*/
        <c:if test="${empty chartSettingForm.jsonStr} ">
        	$("#${ns}chartContainer").append("การแสดงผลผิดพลาด");
        	$("#${ns}chartContainer").addClass("minChartContainer");
        </c:if>
    });
	function F${chartSettingForm.chartInstance}_regenerateItem(id,val,items){
		var i_fiter_prefix = "i_filter_";
		var cnt = $("#"+i_fiter_prefix+id);  
		cnt.empty();
		for(var i=0;i<items.length;i++){
			var opt = $("<option/>");
			opt.attr("value",items[i]["key"]);
			opt.html(items[i]["desc"]);
			cnt.append(opt);
		}
		//cnt.val(val); // *becareful, are u sure to open this?*
	}
	function F${chartSettingForm.chartInstance}_cascade(current){
		var cascade = {};
		var filters = [];
		$("#${ns}_chartSettingForm .internal_filter").each(function(){
			filters.push({  "id": F${ns}_getIdenfromElementId($(this).attr("id"),'i_filter_') 
				, "value" : $(this).val() 
				, "param" : $(this).attr("data-filter")
				, "type" : F${ns}_checkControllType($(this))  });
		});

		cascade.filters = filters;
		cascade.cause = current.id.replace("i_filter_","");
		cascade.instance = $("form#${ns}_chartSettingForm #chartInstance").val();
		
		$("#${chartSettingForm.chartInstance}_cascadeWaiting").show();
		$("#${ns}_chartSettingForm .internal_filter").prop("disabled",true);
		
		$.ajax({
   	 		dataType: "json",
   	 		url:"<%=cascadeInternalFilter%>",
   	 		data: { cascade: JSON.stringify(cascade) },
   	 		success:function(data){
   	 			if(data["header"]["success"]>0){
	   	 			for(var i = 0 ; i<data["content"].length;i++){
	   	 				F${chartSettingForm.chartInstance}_regenerateItem(data["content"][i]["id"],data["content"][i]["value"],data["content"][i]["item"]);
	   	 			}
   	 			}else{ /* do nothing */}
   	 		},complete:function(data){
	   	 		setTimeout( function() {
		 			$("#${chartSettingForm.chartInstance}_cascadeWaiting").hide();
		 			$("#${ns}_chartSettingForm .internal_filter").prop("disabled",false);
   	 			},1000);  //wait min 1 second
   	 		}
   	 	}); // end ajax
	} //end function
	function F${ns}_submitAjax(){
		//  find all portlet  for global  
		var filter = { };
		var filters = []
		var gfilters = [];
		var ifilters = [];
		$(".global_filter").each(function(){
			gfilters.push({  "id": F${ns}_getIdenfromElementId($(this).attr("id"),'g_filter_') 
				, "value" : $(this).val()
				, "param" : $(this).attr("data-filter") 
				, "text" : F${ns}_retriveText($(this))
				, "type" : F${ns}_checkControllType($(this)) });
		});
		$("#${ns}_chartSettingForm .internal_filter").each(function(){
			ifilters.push({  "id": F${ns}_getIdenfromElementId($(this).attr("id"),'i_filter_') 
				, "value" : $(this).val() 
				, "param" : $(this).attr("data-filter")
				, "text" : F${ns}_retriveText($(this))
				, "type" : F${ns}_checkControllType($(this))  });
		});
		filter.filters = gfilters.concat(ifilters);
		filter.instance = $("form#${ns}_chartSettingForm #chartInstance").val();
		$.ajax({
   	 		dataType: "json",
   	 	 	contentType: "application/json; charset=utf-8",
   	 		url:"<%=buildChartAjax%>",
	 		data: { filter: JSON.stringify(filter) },
	 		success:function(data){
	 			if(data["header"]["success"]>0){
	 				//new title from paramFilter
	 				data["content"]["chartJson"] = F${ns}_generateTilteAndSubTitle(data["content"]["chartJson"],ifilters);
	 				F${ns}_buildChart(data["content"]["chartType"],data["content"]["chartJson"]);
	 			}
	 		} //end success 
		});
		return false;
	}
	function F${ns}_getIdenfromElementId(eleId,replaceStr){
		eleId.replace(replaceStr,"");
		return eleId.replace(replaceStr,"");
	}
	function F${ns}_buildChart(chartType,chartJsonObj){
		var revenueChart = new FusionCharts({
            "type": chartType,
            "renderAt": "${ns}chartContainer",
            "width": "100%", // 500
            "height": "${chartSettingForm.chartHeight}", // chartHeight,
            "dataFormat": "json",
            "dataSource": JSON.parse(chartJsonObj)
        });
        revenueChart.render();
	}
	function F${ns}_checkControllType(jqObj){
		var type ="";
		var tagName = jqObj.prop("tagName");
		if(tagName.toLowerCase()=="select" && !jqObj.prop("multiple")){
			type="select";
		}else if(tagName.toLowerCase()=="select" && jqObj.prop("multiple")){
			type="multiple select";
		}else if(tagName.toLowerCase()=="input" && jqObj.prop("type")=="text"){
			type="manual input";
		}
		return type;
	}
	function F${ns}_retriveText(jqObj){
		var text = "";
		var tagName = jqObj.prop("tagName");
		if(tagName.toLowerCase()=="select" && !jqObj.prop("multiple")){
			text = jqObj.find('option:selected').text();
		}else if(tagName.toLowerCase()=="select" && jqObj.prop("multiple")){
			jqObj.find('option:selected').each(function(){
				text = text+","+$(this).text();
			});
		}else if(tagName.toLowerCase()=="input" && jqObj.prop("type").toLowerCase()=="text"){
			text = jqObj.val();
		}
		return text;
	}
	function F${ns}_generateTilteAndSubTitle(chartJsonString,filterList){
		var json = JSON.parse(chartJsonString);
		var title=json.chart.caption;
		var subTitle=json.chart.subCaption;
		for(var i=0;i<filterList.length;i++){
			var syntax = "_"+filterList[i].param+"_";
			title = title.replace(syntax,filterList[i].text);
			subTitle = subTitle.replace(syntax,filterList[i].text);
		}
		json.chart.caption = title;
		json.chart.subCaption = subTitle;
		return JSON.stringify(json);
	}
</script>
        </body>
</html>

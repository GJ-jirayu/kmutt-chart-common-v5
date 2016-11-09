<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="javax.portlet.PortletURL"%>
<%--
  Created by IntelliJ IDEA.
  User: imake
  Date: 07/09/2015
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<html>

<portlet:resourceURL var="cascadeGlobalFilter" id="cascadeGlobalFilter"></portlet:resourceURL>  
<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
	<head>
    <title></title>
    <style>
     	.aui select{
	    	width:auto !important;
	    	width:auto !important;
	    	max-width:98%;
	    }
    </style>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
	<script>
		 $(document).ready(function () {
			 $("#${ns}comment_bt").popover({
		            html:true
		      });
		 });
		function regenerateItem(id,val,items){
			var g_fiter_prefix = "g_filter_";
			var cnt = $("#"+g_fiter_prefix+id);  
			cnt.empty();
			for(var i=0;i<items.length;i++){
				var opt = $("<option/>");
				opt.attr("value",items[i]["key"]);
				opt.html(items[i]["desc"]);
				cnt.append(opt);
			}
			//cnt.val(val); // *becareful, are u sure to open this?*
		}
		function cascadeGlobal(current){
			var filterId = current.id.replace("g_filter_","");
			var factor = [];
			var limitor = ":#:";
			var seperate = ":&:";
			$("#globalFilterForm .global_filter").each(function(){
				if($(this).prop("multiple")){
					 var select = this;
					 var selected = [];
					 for (var i = 0; i < select.length; i++) {
					        if (select.options[i].selected) selected.push(select.options[i].value);
					 }
					 factor.push(this.id.replace("g_filter_","")+seperate+selected.join(","));
				}else{
					factor.push(this.id.replace("g_filter_","")+seperate+this.value);
				}
			});
			$("#cascadeWaiting").show();
			$(".global_filter").prop("disabled",true);
			$.ajax({
	   	 		dataType: "json",
	   	 		url:"<%=cascadeGlobalFilter%>",
	   	 		data: { filterId : filterId , factor : factor.join(limitor)  },
	   	 		success:function(data){
	   	 			setTimeout( function() {
	   	 				$("#cascadeWaiting").hide();
		   	 			$(".global_filter").prop("disabled",false);
		   	 			for(var i = 0 ; i<data["content"].length;i++){
		   	 				regenerateItem(data["content"][i]["id"],data["content"][i]["value"],data["content"][i]["item"]);
		   	 			}
	   	 			 }, 1000);
	   	 		},failure:function(data){
	   	 			setTimeout( function() {
	   	 				$("#cascadeWaiting").hide();
   	 					$(".global_filter").prop("disabled",false);
	   	 			},1000)
	   	 		} //end fali 
	   	 	}); // end ajax
		} //end function
	</script>
</head>
<body>
<form:form id="globalFilterForm" modelAttribute="globalFilterForm" method="post" name="globalFilterForm"
           action="${formAction}" enctype="multipart/form-data" style="margin:0 0 0 0">
	<img id="cascadeWaiting"  src="<c:url value="/resources/images/rotate.gif"/>" style="cursor:pointer;width:22px;height: 22px;padding-left:5px;display:none;" />
	<c:if test="${not empty filterList}">
        <c:forEach items="${filterList}" var="filter" varStatus="loop">
        	<div style="display:inline-block">
            	&nbsp;&nbsp; ${filter.title}   &nbsp;&nbsp;
           		<c:choose>
           			<c:when test="${filter.valueType.equals('manual input') }">
           				<input type="text" id="g_filter_${filter.filterId}" name="g_filter_${filter.filterId}" class="global_filter" value="${filter.selectedValue}" data-filter="${filter.filterName}" onchange="cascadeGlobal(this)" />
           			</c:when>
           			<c:when test="${filter.valueType.equals('select')}">
		                <select id="g_filter_${filter.filterId}" name="g_filter_${filter.filterId}" class="global_filter" data-filter="${filter.filterName}" onchange="cascadeGlobal(this)">
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
		            	<select id="g_filter_${filter.filterId}" name="g_filter_${filter.filterId}" class="global_filter" multiple data-filter="${filter.filterName}" onchange="cascadeGlobal(this)">
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
	   	<button type="submit"class="btn btn-primary" style="margin-bottom:4px;">Submit</button>
    </div>
    <div style="display:inline-block;padding:6px 1px 1px 5px;float:right;">
    	<a tabindex="0" id="${ns}comment_bt"
            data-toggle="popover" data-trigger="focus" title="Comment"
           data-content="${globalFilterForm.comment}">
            <img id="${ns}linktox" src="<c:url value="/resources/images/comment.jpg"/>" style="cursor:pointer;width:16px;height: 16px;padding-left:5px" />
        </a>
    </div>
</form:form>
<script>
$('#globalFilterForm input[type="text"]').keypress(function(e){
    if ( e.which == 13 ) // Enter key = keycode 13
    { 
    	e.preventDefault();
        return false;
    }
});
</script>
</body>
</html>

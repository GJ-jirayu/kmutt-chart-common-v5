<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: imake
  Date: 07/09/2015
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
  <title></title>
</head>
<body>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
show Filter<br/>
<form:form id="filterForm" modelAttribute="filterForm" method="post" name="filterForm"
           action="${formAction}" enctype="multipart/form-data">
  <form:select path="mode">
    <form:option value="Day">Day</form:option>
    <form:option value="Month">Month</form:option>
    <form:option value="Year">Year</form:option>
  </form:select>
  <button type="submit">Submit</button>
  <%--
  <a href="<portlet:actionURL name="pitchBall"></portlet:actionURL>">Pitch!</a>
  --%>
</form:form>

</body>
</html>

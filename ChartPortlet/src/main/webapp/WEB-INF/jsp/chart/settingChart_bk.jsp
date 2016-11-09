<%--
  Created by IntelliJ IDEA.
  User: imake
  Date: 13/09/2015
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:set var="ns"><portlet:namespace/></c:set>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
<html>
<head>
  <title></title>
</head>
<b>

  <form:form id="dashboardForm" modelAttribute="dashboardForm" method="post" name="chartSettingFormm"
             action="${formAction}" enctype="multipart/form-data">
  <b>Data Source  :</b><br/>
  <input type="radio" name="service_select"/> <b>Services: </b>
  <form:select path="dataSource" >
  <form:option value="column2d">
  ผลงานวิจัยจำแนกตามแหล่งที่ได้รับการเผยแพร่
  </form:option>
  <form:option value="pie3d">
  ผลงานทรัพย์สินทางปัญญา-สิทธิบัตรและอนุสิทธิบัตร
  </form:option>
  <form:option value="pie3dc">
  เงินวิจัยและบริการวิชาการจำแนกตามแหล่งทุน
  </form:option>


  </form:select><span style="padding-left: 10px" ></span><br/><br/>
  <input type="radio" name="service_select"/>
  <b>Data ad hoc : </b>
    <form:textarea path="dataAdhoc"></form:textarea>
  <br/>
  <button  type="button">Load </button><br/><br/>
  <b>Chart</b><br/>
  Chart Title:<input type="text"/><br/>
  Chart Size:Width:<input style="width:100px" type="text"/>Height:<input style="width:100px" type="text"/><br/>
  Chart Type :
  <form:select path="chartType" >
  <form:option value="column2d">Column 2D</form:option>
    <%--
     <form:option value="pie3d">Pie 3D</form:option>
     <form:option value="angulargauge">Real-time Angular</form:option>
     --%>
  <form:option value="pyramid">Pyramid Chart</form:option>
  <form:option value="mscolumn3dlinedy">Multi-series Column 3D + Line - Dual Y Axis</form:option>
  <form:option value="mscombidy2d">Multi-series 2D Dual Y Combination Chart (Column + Line + Area)</form:option>

  <form:option value="mscolumn2d">Multi-series Column 2D</form:option>
  <form:option value="mscombi2d">Multi-series 2D Single Y Combination Chart (Column + Line + Area)</form:option>

  <form:option value="mscolumn3d">Multi-series Column 3D</form:option>
  <form:option value="mscombi3d">Multi-series 3D Single Y Combination Chart (Column + Line + Area)</form:option>

  <form:option value="pie2d">Pie 2D</form:option>
  <form:option value="pie3d">Pie 3D</form:option>
  <form:option value="multilevelpie">Multi-level Pie Chart</form:option>

  <form:option value="angulargauge">Real-time Angular</form:option>

  <form:option value="msline">Multi-series Line 2D</form:option>
  <form:option value="MSArea">Multi-series Area 2D</form:option>
  <form:option value="hbullet">Horizontal bullet graph</form:option>
  <form:option value="bar2d">Bar 2D</form:option>
  <form:option value="stackedcolumn2d">Stacked Column 2D</form:option>
  <!--
        <form:option value="stackedcolumn2d_v2">Stacked Column 2D V2</form:option>
        -->
  <form:option value="heatmap">Heat Map Chart</form:option>
  <form:option value="gantt">Gantt Chart</form:option>
    <%--
            line
            area2d


            stackedarea2d
            stackedcolumn2dline
            --%>
  </form:select>
    <%--
--%>
    <form:hidden path="chartInstance"></form:hidden>
  <br/>
  Advance Property: <form:textarea path="advProp"></form:textarea><br/>


    <%--
  <textarea>${dataSourceJson}</textarea>
  --%>
    <%--
    <form:textarea path="tab">

    </form:textarea>
    --%>
    <%--
    <br/><br/><br/>
    <b>Filters for this chart</b><br/>
<input type="checkbox"> Some label
    <select style="width:100px" >
        <option >Time</option>
    </select>
    <select style="width:100px" >
        <option >EQUAL</option>
    </select>
    <input type="text"><br/>

    <input type="checkbox"> Some label
    <select style="width:100px" >
        <option >Time</option>
    </select>
    <select style="width:100px" >
        <option >EQUAL</option>
    </select>
    <input type="text"><br/>


    <input type="checkbox"> Some label
    <select style="width:100px" >
        <option >Time</option>
    </select>
    <select style="width:100px" >
        <option >EQUAL</option>
    </select>
    <input type="text"><br/><br/>
<input type="checkbox"> <b>Enable drill down</b><br/><br/><br/>
--%>
  <button type="submit">Submit</button>
  </form:form>
  </body>
</html>

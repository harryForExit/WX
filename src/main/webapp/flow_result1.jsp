<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>流量查询</title>

  <link rel="stylesheet" href="<%=basePath%>style/main.css"/>
    <link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
    <link rel="stylesheet" href="<%=basePath%>style/example.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js" ></script>
</head>
<body ontouchstart>
<div class="container js_container">
  <div class="page">
    <div class="weui_cells_title">剩余流量查询</div>
    
    <c:if test="${status == '1'}">	
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_hd">
          <label class="weui_label">卡号</label>
        </div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="text" placeholder="${number}" disabled="true"/>
        </div>
      </div>
    </div>
    <div class="weui_uploader_bd weui_cell">
      <ul class="flow_box">
        <li class="flow_nav" style="">总量<br>
          ${zl}</li>
        <li class="flow_nav" style="">已使用量<br>
          ${ysyll}</li>
        <li class="flow_nav" style="">剩余量<br>
          ${syll}</li>
        <li class="flow_nav" style="">套餐名称<br>
          ${tcname}</li>
        <li class="flow_nav" style="">开始时间<br>
          ${starttime}</li>
        <li class="flow_nav" style="">结束时间<br>
          ${endtime}</li>
      </ul>
    </div>
     </c:if>
      <c:if test="${status != '1'}">
       <table class="table table-bordered" style="width:60%;border:none;">
          <tr>
		   <td align="center"  style="color:red;font-size:20px;border:none;">暂无卡号信息</td>
		  </tr>
       </table>
    </c:if>	
   <!--  <div class="weui_btn_area"> <a href="from_smrz_2.html" class="weui_btn weui_btn_primary">查询</a> </div> -->
  </div>
</div>
</body>
</html>

<!DOCTYPE html>
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
<title>实名认证</title>
<link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
<link rel="stylesheet" href="<%=basePath%>style/example.css"/>
</head>
<body ontouchstart>
<div class="container js_container">
  <div class="page">
  <div class=""><img src="images/process_3.png" width="100%"></div>
    <div class="weui_msg">
    <c:if test="${status == '1'}">	
      <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
      <div class="weui_text_area">
        <h2 class="weui_msg_title">等待审核</h2>
        <p class="weui_msg_desc">实名认证信息提交成功！</p>
      </div>
     </c:if>
     <c:if test="${status != '1'}">	
      <div class="weui_icon_area"><i class="weui_icon_msg weui_icon_warn"></i></div> 
       <div class="weui_text_area">
        <h2 class="weui_msg_title">提交失败</h2>
        <p class="weui_msg_desc">已提交或提交数据有误！</p>
      </div>
      </c:if>
     <!--  <div class="weui_opr_area">
        <p class="weui_btn_area"> <a href="javascript:;" class="weui_btn weui_btn_primary">确定</a> <a href="javascript:;" class="weui_btn weui_btn_default">取消</a> </p>
      </div> -->
    </div>
  </div>
</div>
</body>
</html>

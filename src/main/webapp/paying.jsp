<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/21
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js" ></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/weixinPay.js" ></script>
<body>
<input type="text" id="appId" value="${appId}">
<input type="text" id="sign" value="${sign}">
<input type="text" id="package" value="${pg}">
<input type="text" id="nonceStr" value="${nonceStr}">
<input type="text" id="timeStamp" value="${timeStamp}">
<input type="button" onclick="pay()" value="立即支付">
</body>

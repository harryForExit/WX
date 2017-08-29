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
<script type="text/javascript" src="<%=basePath%>js/weixinPay.js" ></script>
<body>
<input type="text" id="appid" value="${appid}">
<input type="text" id="sign" value="${sign}">
<input type="text" id="prepay_id" value="${prepay_id}">
<input type="text" id="nonce_str" value="${nonce_str}">
<input type="button" onclick="pay()" value="立即支付">
</body>

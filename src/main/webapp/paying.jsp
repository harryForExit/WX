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
<body onload="pay()">
<input type="hidden" id="appId" value="${appId}">
<input type="hidden" id="sign" value="${sign}">
<input type="hidden" id="package" value="${pg}">
<input type="hidden" id="nonceStr" value="${nonceStr}">
<input type="hidden" id="timeStamp" value="${timeStamp}">

<script>
    function onBridgeReady(){

        var appId = $("#appId").val()+"";
        var timeStamp = $("#timeStamp").val()+"";
        var nonceStr = $("#nonceStr").val()+"";
        var pg = $("#package").val()+"";
        var signType = "MD5";
        var paySign = $("#sign").val()+"";
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId":appId,
                "timeStamp":timeStamp,
                "nonceStr":nonceStr,
                "package":"prepay_id="+pg,
                "signType":"MD5",
                "paySign":paySign
            },

            function(res){
                var str = JSON.stringify(res);
                alert(str);
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {

                    alert("支付成功");
                    window.location.href = "<%=basePath%>buySomething";
                }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
            }
        );
    }


    function pay(){

        if (typeof WeixinJSBridge == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else{
            onBridgeReady();
        }

    }
</script>
</body>

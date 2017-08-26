<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/19
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js" ></script>
   <%-- <script type="text/javascript" src="<%=basePath%>js/weixinPay.js" ></script>--%>
    <link rel="stylesheet" href="<%=basePath%>/style/main.css"/>
    <link rel="stylesheet" href="<%=basePath%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=basePath%>/style/example.css"/>
</head>
<body ontouchstart>
<div class="container js_container">
    <div class="page">
        <div class="weui_cells_title">购买套餐</div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label">卡号</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="number" placeholder="请输入物联网卡号"/>
                </div>
            </div>
        </div>
        <div class="page-bd">

            <div class="weui-flex">
                <div class="weui-flex-item"><div class="placeholder_red" data-value = 4>4元<br>
                    30MB</div></div>
                <div class="weui-flex-item"><div class="placeholder" data-value = 8>8元<br>
                    100MB</div></div>
                <div class="weui-flex-item"><div class="placeholder" data-value = 16>16元<br>
                    300MB</div></div>
            </div>
            <div class="weui-flex">
                <div class="weui-flex-item"><div class="placeholder" data-value = 24>24元<br>
                    500MB</div></div>
                <div class="weui-flex-item"><div class="placeholder" data-value = 40>40元<br>
                    1GB</div></div>
                <div class="weui-flex-item"><div class="placeholder" data-value = 80>80元<br>
                    3GB</div></div>
            </div>
        </div>
        <div class="weui_cells_title">支付金额：<span id="money">4</span>元</div>
        <div class="weui_btn_area"> <a href="javascript:;" class="weui_btn weui_btn_primary">立即充值</a> </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".weui-flex-item").click(function () {
            var divElement = $($(this).find("div")[0]);
            var money = divElement.attr("data-value");
            console.log(money+"----------------");
            $($(".weui_cells_title").find("span")[0]).text(money);//设置支付金额
            var selectElement =
                $(".weui-flex-item").find("div").each(function () {
                if ($(this).attr("class") ==="placeholder_red"){
                    return $(this);
                }
            });
            selectElement.attr("class","placeholder");//取消当前选中状态
            divElement.attr("class","placeholder_red");//设置为未选中状态
        })

        $(".weui_btn_area").click(function () {
            var money = $("#money").val();
            window.location.href="/weixinPay?money="+money;
        })
    })


</script>
</html>


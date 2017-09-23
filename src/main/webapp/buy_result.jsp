<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/18/018
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
    <link rel="stylesheet" href="style/main.css"/>
    <link rel="stylesheet" href="style/weui.css"/>
    <link rel="stylesheet" href="style/example.css"/>
</head>
<body ontouchstart>
<div class="container js_container">
    <div class="page">
        <div class="weui_msg">
            <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
            <div class="weui_text_area">
                <h2 class="weui_msg_title">充值成功</h2>
            </div>
        </div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd">充值号码：${number}</div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">付款金额：${price} 元</div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">充值信息：${desc}流量</div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">下单时间：${date}</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/19
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>订购套餐</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js" ></script>
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
                    <input class="weui_input" id="number" type="number" placeholder="请输入物联网卡号"/>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd">
                    <label class="weui_label">套餐类型</label>
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input type="radio" name="type" onclick="if(checked){type1.style.display='block';type2.style.display='none';}" checked="checked">月加餐包
					<input type="radio" name="type" onclick="if(checked){type1.style.display='none';type2.style.display='block';}">月固定流量包
                </div>
            </div>
        </div>
        <div class="page-bd" id="type1" style="display: block;">
            <c:forEach items="${addMeals}" var="addmeal" varStatus="i">
            <c:if test="${i.index ==0 || i.index % 3 == 0}">
                <div class="weui-flex">
            </c:if>
                    <div class="weui-flex-item">
                        <div class="placeholder" data-mealid="${addmeal.mealid}" data-value = "${addmeal.price}">${addmeal.price}元<br>
                                ${addmeal.desc}</div>
                    </div>
                <c:if test="${(i.index+1) % 3 == 0}">
                </div>
            </c:if>
            </c:forEach>
        </div>

        <div class="page-bd" id="type2" style="display: none;">

            <c:forEach items="${monthMeals}" var="monthmeal" varStatus="a">
            <c:if test="${a.index ==0 || a.index % 3 == 0}">
            <div class="weui-flex">
                </c:if>
                <div class="weui-flex-item"><div class="placeholder" data-mealid="${monthmeal.mealid}" data-value = "${monthmeal.price}">${monthmeal.price}元<br>
                    ${monthmeal.desc}</div></div>

                <c:if test="${(a.index+1) % 3 == 0}">
            </div>
            </c:if>
            </c:forEach>
        </div>
        <div class="weui_cells_title">支付金额：<span id="money">1.2</span>元</div>
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
        });

        $(".weui_btn_area").click(function () {
            var money = $("#money").html();
            var number = $("#number").val();
            var mealid = $($(".weui-flex-item").find(".placeholder_red")[0]).attr("data-mealid");
            if (number == null||number=="" || mealid == null){
                alert("请填写完整信息")
            }else {
                window.location.href="<%=basePath%>weixinPay?money="+money+"&number="+number+"&mealid="+mealid ;
            }

        })
    })
</script>
</html>


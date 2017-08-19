<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
    <link rel="stylesheet" href="<%=basePath%>style/main.css"/>
    <link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
    <link rel="stylesheet" href="<%=basePath%>style/example.css"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js" ></script>
</head>
<body ontouchstart>
    <div class="container js_container">
        <div class="page">
        <div class="weui_cells_title">剩余流量查询</div>
        <form action="index1" id="loginForm1" method="post" name="loginForm1" id="loginForm1">
                <div class="weui_cells weui_cells_form">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">卡号</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="number" name="number" id="number" placeholder="请输入物联网卡号"/>
                        </div>
                    </div>
                    
                    
                </div>
                <div class="weui_btn_area">
               <!--  <a href="from_smrz_2.html" class="weui_btn weui_btn_primary">查询</a> -->
                 <input type="button" class="weui_btn weui_btn_primary" onclick="cc();" value="查询"/>
                </div>
        </div>
    </div>
</body>

<script type="text/javascript">
function cc(){

	  var number=$("#number").val();
	 
	      if(number==null||number==""){
	    	  alert("卡号不能为空");	
			return false;
		  }else{
			  
			  $("#loginForm1").submit();
				return false;
			
		  } 
	  }


</script>
</html>

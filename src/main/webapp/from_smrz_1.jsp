<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="util.TokenProcessor" %>
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
<%
    //获取令牌类实例

    TokenProcessor processor = TokenProcessor.getInstance();
    //获取令牌值

    String token = processor.getToken(request);
   %>
    <div class="container js_container">
        <div class="page">
        <div class=""><img src="img/process_1.png" width="100%"></div>
       
        <div class="weui_cells_title">表单</div>
          <form action="index"  method="post" name="loginForm1" id="loginForm1">
          <input type="hidden" name="_form_token" value="{$_form_token}" />
          <input type="hidden" name="org.sunxin.token" value="<%=token%>"/>
                <div class="weui_cells weui_cells_form">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">卡号</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="number" name="cardno" id="cardno" placeholder="请输入物联网卡号"/>
                        </div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">姓名</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="text" name="xm" id="xm" placeholder="请输入办理时所用姓名"/>
                        </div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">身体证</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="number"  name="sfzmhm" id="sfzmhm"  placeholder="请输入办理时所用身份证"/>
                        </div>
                    </div>
                    
                </div>
                <div class="weui_btn_area">
                <input type="button" class="weui_btn weui_btn_disabled weui_btn_primary" onclick="dd();" value="下一步"/>
                </div>
              </form>
        </div>
    </div>
</body>

<script type="text/javascript">
function dd(){

	  var sfzmhm=$("#sfzmhm").val();
	  var xm=$("#xm").val();
	  var cardno=$("#cardno").val();
	      if(sfzmhm==null||sfzmhm==""){
	    	  alert("身份证号不能为空");	
			return false;
		  }else if(xm==null||xm==""){
			  alert("姓名不能为空");	
			  return false;
		  }else if(cardno==null||cardno==""){
			  alert("卡号不能为空");	
			  return false;
		  }else{
			  
			  $("#loginForm1").submit();
				return false;
			 /*  $("#loginForm1").submit();
				return false; */
		  } 
	  }


</script>

</html>

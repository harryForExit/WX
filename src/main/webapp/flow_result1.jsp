<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
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
    <div class="weui_cells_title">剩余流量查询</div>
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
    <div class="weui_uploader_bd weui_cell">
      <ul class="flow_box">
        <li class="flow_nav" style="">4元<br>
          30MB</li>
        <li class="flow_nav" style="">8元<br>
          100MB</li>
        <li class="flow_nav" style="">16元<br>
          300MB</li>
        <li class="flow_nav" style="">24元<br>
          500MB</li>
        <li class="flow_nav" style="">40元<br>
          1GB</li>
        <li class="flow_nav" style="">80元<br>
          3GB</li>
      </ul>
    </div>
    <div class="weui_btn_area"> <a href="from_smrz_2.html" class="weui_btn weui_btn_primary">查询</a> </div>
  </div>
</div>
</body>
</html>

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
<link rel="stylesheet" href="<%=basePath%>style/weui.css"/>
<link rel="stylesheet" href="<%=basePath%>style/example.css"/>
</head>
<body ontouchstart>
<div class="container js_container">
  <div class="page">
    <div class=""><img src="img/process_2.png" width="100%"></div>
    <div class="weui_cells_title">上传身份证照片</div>
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_uploader">
            <div class="weui_uploader_hd weui_cell">
              <div class="weui_cell_bd weui_cell_primary">身份证正面+卡照片</div>
            </div>
            <div class="weui_uploader_bd">
              <div class="weui_uploader_input_wrp">
                <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_uploader">
            <div class="weui_uploader_hd weui_cell">
              <div class="weui_cell_bd weui_cell_primary">身份证反面+卡照片</div>
            </div>
            <div class="weui_uploader_bd">
              <div class="weui_uploader_input_wrp">
                <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple />
              </div>
            </div>
          </div>
        </div>
      </div>


      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_uploader">
            <div class="weui_uploader_hd weui_cell">
              <div class="weui_cell_bd weui_cell_primary">手持身份证照片</div>
            </div>
            <div class="weui_uploader_bd">
              <div class="weui_uploader_input_wrp">
                <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="weui_btn_area">
                <a href="from_smrz_2.html" class="weui_btn weui_btn_disabled weui_btn_primary">提交审核</a>
                </div>
    </div>
  </div>

</body>
</html>

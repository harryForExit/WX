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

	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js" ></script>
	<script type="text/javascript" src="<%=basePath%>js/dist/lrz.bundle.js" ></script>
   
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
                <input class="weui_uploader_input" id="payfile" name="myfile" onchange="fileChange(this)"  type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple /> 
        
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


<script type="text/javascript">  
function fileChange(that){  
    var filepath=$(that).val();  
    if(filepath=="")  
    {  
        return;  
    }  
    var extStart=filepath.lastIndexOf(".");  
    var ext=filepath.substring(extStart,filepath.length).toUpperCase();  
    if(".jpg|.png|.bmp|.jpeg".toUpperCase().indexOf(ext.toUpperCase())==-1){  
       alert("只允许上传jpg、png、bmp、jpeg格式的图片");  
        return false;  
    }  
 //以图片宽度为800进行压缩  
lrz(that.files[0], {  
     width: 800  
   })  
.then(function (rst) {  
        //压缩后异步上传  
        $.ajax({  
        url : "<%=request.getContextPath()%>/fileUploadPicture",  
        type: "POST",  
        data : {  
            imgdata:rst.base64//压缩后的base值  
        },  
        dataType:"json",  
        cache:false,  
        async:false,  
        success : function(data) {  
        if(data.success)  
            {  
                alert(data.message);///data.message为上传成功后的文件路径  
            }else{  
                alert(data.message);///data.message为上传失败原因  
            }  
                          
                    },  
    error : function(){  
            alert("上传失败");  
                    }  
                });  
     });  
}  
    </script>  
</body>
</html>

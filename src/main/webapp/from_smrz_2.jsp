<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>实名认证</title>
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
                <input class="weui_uploader_input" id="zmfile" name="zmfile" onchange="fileChange(this)"  type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple /> 
     			 <img id="image" > 
        		 
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
                <!-- <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple /> -->
                <input class="weui_uploader_input" id="fmyfile" name="fmfile" onchange="fileChange1(this)"  type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple /> 
                <img id="image1" > 
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
                <!-- <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple /> -->
                <input class="weui_uploader_input" id="scyfile" name="scfile" onchange="fileChange2(this)"  type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple /> 
                <img id="image2" > 
              </div>
            </div>
          </div>
        </div>
      </div>
        <form action="sc" id="sc" method="post" name="sc" id="sc">
      <input  type="hidden" name="xm"  value="${xm}" />
      <input  type="hidden"  name="cardno" value="${cardno}" />
      <input  type="hidden"  name="sfzmhm" value="${sfzmhm}" />
      
      <input  type="hidden"  id="zm"  name="zm"  />
      <input  type="hidden"  id="fm"  name="fm"  />
      <input  type="hidden"  id="scc"  name="scc" />
      </form>
      <div class="weui_btn_area">
                <!-- <a href="from_smrz_2.html" class="weui_btn weui_btn_disabled weui_btn_primary">提交审核</a> -->
                 <input type="button" class="weui_btn weui_btn_disabled weui_btn_primary" onclick="dd();" value="提交审核"/>
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
                alert(data.success);
                //alert(data.photoname);///data.message为上传成功后的文件路径  
                 $("#image").attr("src",'http://localhost:8080/wx/photos/'+data.photoname+'.jpg'); 
                 $("#zm").val(data.zm);
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


function fileChange1(that){  
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
        url : "<%=request.getContextPath()%>/fileUploadPicture1",  
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
                alert(data.success);
                //alert(data.photoname);///data.message为上传成功后的文件路径  
                 $("#image1").attr("src",'http://localhost:8080/wx/photos/'+data.photoname+'.jpg'); 
                 $("#fm").val(data.fm);
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

function fileChange2(that){  
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
        url : "<%=request.getContextPath()%>/fileUploadPicture2",  
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
                alert(data.success);
                //alert(data.photoname);///data.message为上传成功后的文件路径  
                 $("#image2").attr("src",'http://localhost:8080/wx/photos/'+data.photoname+'.jpg'); 
                 $("#scc").val(data.sc);
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

function dd(){

	
			  $("#sc").submit();
				return false;
		
	  }
    </script>  
</body>
</html>

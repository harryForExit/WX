package servletdepartment;

import java.io.File;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toft.utils.json.JSONObject;

import sun.misc.BASE64Decoder;
import util.HttpClientUtils;
import util.Result;
import util.TokenProcessor;

//import com.lanxin.test.bean.bh;
//import com.lanxin.test.service.clsbdhservice;
//import com.lanxin.test.service.clsbdhserviceimpl;
//import com.lanxin.test.webservice.bean.WsQueryClxxOutBean;


public class fileUploadPicture2 extends HttpServlet{
	

	public static String formUpload(String urlStr, Map<String, String> textMap,  
	        Map<String, String> fileMap) {  
	    String res = "";  
	    HttpURLConnection conn = null;  
	    String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
	    try {  
	        URL url = new URL(urlStr);  
	        conn = (HttpURLConnection) url.openConnection();  
	        conn.setConnectTimeout(5000);  
	        conn.setReadTimeout(30000);  
	        conn.setDoOutput(true);  
	        conn.setDoInput(true);  
	        conn.setUseCaches(false);  
	        conn.setRequestMethod("POST");  
	        conn.setRequestProperty("Connection", "Keep-Alive");  
	        conn  
	                .setRequestProperty("User-Agent",  
	                        "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
	        conn.setRequestProperty("Content-Type",  
	                "multipart/form-data; boundary=" + BOUNDARY);  

	        OutputStream out = new DataOutputStream(conn.getOutputStream());  
	        // text  
	        if (textMap != null) {  
	            StringBuffer strBuf = new StringBuffer();  
	            Iterator iter = textMap.entrySet().iterator();  
	            while (iter.hasNext()) {  
	                Map.Entry entry = (Map.Entry) iter.next();  
	                String inputName = (String) entry.getKey();  
	                String inputValue = (String) entry.getValue();  
	                if (inputValue == null) {  
	                    continue;  
	                }  
	                strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
	                        "\r\n");  
	                strBuf.append("Content-Disposition: form-data; name=\""  
	                        + inputName + "\"\r\n\r\n");  
	                strBuf.append(inputValue);  
	            }  
	            out.write(strBuf.toString().getBytes());  
	        }  

	        // file  
	        if (fileMap != null) {  
	            Iterator iter = fileMap.entrySet().iterator();  
	            while (iter.hasNext()) {  
	                Map.Entry entry = (Map.Entry) iter.next();  
	                String inputName = (String) entry.getKey();  
	                String inputValue = (String) entry.getValue();  
	                if (inputValue == null) {  
	                    continue;  
	                }  
	                File file = new File(inputValue);  
	                String filename = file.getName();  
	                String contentType = new MimetypesFileTypeMap()  
	                        .getContentType(file);  
	                if (filename.endsWith(".png")) {  
	                    contentType = "image/png";  
	                }  
	                if (contentType == null || contentType.equals("")) {  
	                    contentType = "application/octet-stream";  
	                }  

	                StringBuffer strBuf = new StringBuffer();  
	                strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
	                        "\r\n");  
	                strBuf.append("Content-Disposition: form-data; name=\""  
	                        + inputName + "\"; filename=\"" + filename  
	                        + "\"\r\n");  
	                strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  

	                out.write(strBuf.toString().getBytes());  

	                DataInputStream in = new DataInputStream(  
	                        new FileInputStream(file));  
	                int bytes = 0;  
	                byte[] bufferOut = new byte[1024];  
	                while ((bytes = in.read(bufferOut)) != -1) {  
	                    out.write(bufferOut, 0, bytes);  
	                }  
	                in.close();  
	            }  
	        }  

	        byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
	        out.write(endData);  
	        out.flush();  
	        out.close();  

	        // 读取返回数据  
	        StringBuffer strBuf = new StringBuffer();  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                conn.getInputStream()));  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {  
	            strBuf.append(line).append("\n");  
	        }  
	        res = strBuf.toString();  
	        reader.close();  
	        reader = null;  
	    } catch (Exception e) {  
	        System.out.println("发送POST请求出错。" + urlStr);  
	        e.printStackTrace();  
	    } finally {  
	        if (conn != null) {  
	            conn.disconnect();  
	            conn = null;  
	        }  
	    }  
	    return res;  
	} 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			doPost(request,response);
}
	   int count=0;
public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	FileOutputStream outputStream=null;
	 req.setCharacterEncoding("UTF-8");  
	 resp.setCharacterEncoding("UTF-8"); 
     
	 resp.setContentType("text/html;charset=GBK");
   
	 String tp=req.getParameter("tp");
         String imgdata=req.getParameter("imgdata");
	 	String path=imgdata.split(",")[1];
	 	String path1=Base64.decode(path.getBytes()).toString();
	 	
	 	//byte[] path=Base64.decode(imgdata.substring(23).getBytes());
	 	//String re=path.toString();

         //LOGGER.info("[文件上传（fileUploadPicture）][params:imgdata=" + imgdata + "]");  
         BASE64Decoder decoder = new BASE64Decoder();  
        try {  
        	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        	String date = format.format(new Date());
        	String folderFile = "C:\\wx\\photos";	
            //String basePath = req.getRealPath("/upload_files");  
            String imgPath=folderFile+"\\"+date+".jpg";  
            // new一个文件对象用来保存图片，默认保存当前工程根目录  
            File imageFile = new File(imgPath);  
            // 创建输出流  
             outputStream = new FileOutputStream(imageFile);  
            // 获得一个图片文件流，我这里是从flex中传过来的  
            byte[] result = decoder.decodeBuffer(imgdata.split(",")[1]);//解码  
            for (int i = 0; i < result.length; ++i) {  
                if (result[i] < 0) {// 调整异常数据  
                result[i] += 256;  
            }  
        }  
            outputStream.write(result);  

            
            String filepath=folderFile+"\\"+date+".jpg";  
            String urlStr = "http://202.103.25.123:8080/resource/upload";  
            Map<String, String> textMap = new HashMap<String, String>();  
            
            textMap.put("appid", util.Constants.appid);  
            textMap.put("token", util.Constants.token);  
            
            Map<String, String> fileMap = new HashMap<String, String>();  
              
            fileMap.put("resource", filepath);  
              
            String ret = formUpload(urlStr, textMap, fileMap);  
            JSONObject jsonObject = new JSONObject(ret);
            String zp=jsonObject.getString("resource");
            System.out.println(ret);  
    		
            PrintWriter out=resp.getWriter();
            String message="上传成功";
   
            String json="{\"success\":\""+message+"\",\"photoname\":\""+date+"\",\"sc\":\""+zp+"\"}";
            out.write(json);
           /* req.setAttribute("data", imgPath);
            req.setAttribute("message", "success");*/
            //return new Result(true, imgPath);  
        //} catch (AppException e1) {  
            //LOGGER.error("[文件上传（fileUpload）-fastdfs][errors:" + e1 + "]");  
            //return new Result(false, "文件上传失败");  
        } catch (Exception e) {  
           // LOGGER.error("[文件上传（fileUpload）][errors:" + e + "]");  
           // return new Result(false, "文件上传失败");  
        }finally{  
        outputStream.flush();   
        outputStream.close();  
          
        }  
        
        
         String xm=req.getParameter("xm");
     	 String cardno=req.getParameter("cardno");
     	 String sfzmhm=req.getParameter("sfzmhm");
         //req.getRequestDispatcher("/from_smrz_2.jsp").forward(req, resp);
         //req.getRequestDispatcher("/from_smrz_2.jsp").forward(req, resp);
   
         //out.println("你已经提交了表单，同一表单不能提交两次。");
        
 
 
	/*String xm=request.getParameter("xm");
	String cardno=request.getParameter("cardno");
	String sfzmhm=request.getParameter("sfzmhm");
	System.out.println(xm);*/
	//System.out.println(hm);
	//clsbdhservice clsbdhse=new clsbdhserviceimpl();
	try {
		
		/*request.setAttribute("xm", xm);
		request.setAttribute("cardno", cardno);
		request.setAttribute("sfzmhm", sfzmhm);*/
	
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//request.getRequestDispatcher("/from_smrz_2.jsp").forward(request, response);
	
}

 

}  


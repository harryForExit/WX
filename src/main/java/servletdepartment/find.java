package servletdepartment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TokenProcessor;

//import com.lanxin.test.bean.bh;
//import com.lanxin.test.service.clsbdhservice;
//import com.lanxin.test.service.clsbdhserviceimpl;
//import com.lanxin.test.webservice.bean.WsQueryClxxOutBean;

public class find extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			doPost(request,response);
}
	   int count=0;
public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	
	 req.setCharacterEncoding("UTF-8");  
	 resp.setCharacterEncoding("UTF-8"); 
     
	 resp.setContentType("text/html;charset=GBK");
   /*  PrintWriter out=resp.getWriter();
     
     TokenProcessor processor=TokenProcessor.getInstance();
     if(processor.isTokenValid(req))
     {
         try
         {
             Thread.sleep(5000);
         }
         catch(InterruptedException e)
         {
             System.out.println(e);
         }
             
         System.out.println("submit : "+count);
         if(count%2==1)
             count=0;
         else
             count++;*/
         //out.println("success");
         String xm=req.getParameter("xm");
     	 String cardno=req.getParameter("cardno");
     	 String sfzmhm=req.getParameter("sfzmhm");
     	 
     	req.setAttribute("xm", xm);
     	req.setAttribute("cardno", cardno);
     	req.setAttribute("sfzmhm", sfzmhm);
         req.getRequestDispatcher("/from_smrz_2.jsp").forward(req, resp);
         //req.getRequestDispatcher("/from_smrz_2.jsp").forward(req, resp);
    /* }
     else
     {
         processor.saveToken(req);
         //out.println("你已经提交了表单，同一表单不能提交两次。");
         req.getRequestDispatcher("/from_smrz_2.jsp").forward(req, resp);
     }
     out.close();*/
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

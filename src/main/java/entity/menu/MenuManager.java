package entity.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.WeixinUtil;

public class MenuManager {
	 private static Logger log = LoggerFactory.getLogger(MenuManager.class);  
	  
	    public static void main(String[] args) {  
	        // 第三方用户唯一凭证  
	        String appId = "wxba50d95321107e8f";  
	        // 第三方用户唯一凭证密钥  
	        String appSecret = "ea9ae1c3cae3086f6e6eb2e427fb519b";  
	  
	        // 调用接口获取access_token  
	        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
	  
	        if (null != at) {  
	            // 调用接口创建菜单  
	            int result = WeixinUtil.createMenu(getMenu(), at.getToken());  
	  
	            // 判断菜单创建结果  
	            if (0 == result)  
	                log.info("菜单创建成功！");  
	            else  
	                log.info("菜单创建失败，错误码：" + result);  
	        }  
	    }  
	  
	    /** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static Menu getMenu() {  
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("公司概况");  
	        btn11.setType("click");  
	        btn11.setKey("11");  
	  
	        ViewButton btn12 = new ViewButton();  
	        btn12.setName("实名认证");  
	        btn12.setType("view");  
	        btn12.setUrl("http://lwcs.ngrok.cc/wx/from_smrz_1.jsp");  
	        
	        ViewButton btn13 = new ViewButton();  
	        btn13.setName("流量查询");  
	        btn13.setType("view");  
	        btn13.setUrl("http://lwcs.ngrok.cc/wx/flow_check1.jsp"); 
	  
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("歌曲点播");  
	        btn21.setType("click");  
	        btn21.setKey("21");  
	  
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("经典游戏");  
	        btn22.setType("click");  
	        btn22.setKey("22");  
	  
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("经典影视");  
	        btn23.setType("click");  
	        btn23.setKey("23");  
	  
	        CommonButton btn24 = new CommonButton();  
	        btn24.setName("人脸识别");  
	        btn24.setType("click");  
	        btn24.setKey("24");  
	  
	        CommonButton btn25 = new CommonButton();  
	        btn25.setName("聊天唠嗑");  
	        btn25.setType("click");  
	        btn25.setKey("25");  
	  
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("Q友圈");  
	        btn31.setType("click");  
	        btn31.setKey("31");  
	  
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("电影排行榜");  
	        btn32.setType("click");  
	        btn32.setKey("32");  
	  
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("幽默笑话");  
	        btn33.setType("click");  
	        btn33.setKey("33");  
	  
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("关于我们");  
	        mainBtn1.setSub_button(new Button[] { btn11, btn12 });  
	  
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("主营业务");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });  
	  
	      /*  ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("更多体验");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });  */
	  
	        /** 
	         *生成完整菜单
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { btn12, btn13, btn33 });  
	  
	        return menu;  
	    }  
}

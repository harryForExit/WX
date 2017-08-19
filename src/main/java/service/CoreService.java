package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entity.response.Article;
import entity.response.NewsMessage;
import entity.response.TextMessage;
import util.MessageUtil;

/**
 * 核心服务类
 * 
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent =null;

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 回复图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";
				if (("帮助").equals(requestMap.get("Content"))) {
					respContent =null;
					List<Article> articleList = new ArrayList<Article>();
					Article article = new Article();
					article.setTitle("帮助");
					article.setDescription("帮助详情,请点击之后自行百度");
					article.setPicUrl("http://lwcs.ngrok.cc/wx/img/img1.jpg");
					article.setUrl("https://www.baidu.com/");
					articleList.add(article);
					newsMessage.setArticles(articleList);
					newsMessage.setArticleCount(articleList.size());
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";

			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";

			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";

			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "博通广发通讯谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("11")) {
						List<Article> articleList = new ArrayList<Article>();
						 Article article1 = new Article();  
		                    article1.setTitle("公司位置");  
		                    article1.setDescription("");  
		                    article1.setPicUrl("http://lwcs.ngrok.cc/wx/img/weizhi.png");  
		                    article1.setUrl("http://map.baidu.com/?newmap=1&s=con%26wd%3D街道口%26c%3D218&from=alamap&tpl=mapdots");  
		  
		                    Article article2 = new Article();  
		                    article2.setTitle("公司成绩");  
		                    article2.setDescription("");  
		                    article2.setPicUrl("http://lwcs.ngrok.cc/wx/img/img1.jpg");  
		                    article2.setUrl("http://www.qq.com/");  
		  
		                    Article article3 = new Article();  
		                    article3.setTitle("公司合作伙伴");  
		                    article3.setDescription("有很多，你猜猜都有谁");  
		                    article3.setPicUrl("");  
		                    article3.setUrl("");  
		  
		                    articleList.add(article1);  
		                    articleList.add(article2);  
		                    articleList.add(article3);  
		                    newsMessage.setArticleCount(articleList.size());  
		                    newsMessage.setArticles(articleList);  
		                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
					} else if (eventKey.equals("12")) {
						respContent = "公交查询菜单项被点击！";
					} else if (eventKey.equals("13")) {
						respContent = "周边搜索菜单项被点击！";
					} else if (eventKey.equals("14")) {
						respContent = "历史上的今天菜单项被点击！";
					} else if (eventKey.equals("21")) {
						respContent = "歌曲点播菜单项被点击！";
					} else if (eventKey.equals("22")) {
						respContent = "经典游戏菜单项被点击！";
					} else if (eventKey.equals("23")) {
						respContent = "经典影视菜单项被点击！";
					} else if (eventKey.equals("24")) {
						respContent = "人脸识别菜单项被点击！";
					} else if (eventKey.equals("25")) {
						respContent = "聊天唠嗑菜单项被点击！";
					} else if (eventKey.equals("31")) {
						respContent = "Q友圈菜单项被点击！";
					} else if (eventKey.equals("32")) {
						respContent = "电影排行榜菜单项被点击！";
					} else if (eventKey.equals("33")) {
						respContent = "小明理了头发，第二天来到学校，同学们看到他的新发型，笑道：小明，你的头型好像个风筝哦！小明觉得很委屈，就跑到外面哭。哭着哭着～他就飞起来了…………";
					}
				}
			}
			if (respContent != null) {
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}

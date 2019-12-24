package kr.websocket.spring;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ServerEndpoint(value = "/echo.do")
public class WebSocketChat {

	private static final List<Session> sessionList = new ArrayList<Session>();
	private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
	
	public WebSocketChat() {
		System.out.println("WebSocket Server 객체 생성");
	}
	
	@RequestMapping(value = "/chat.do")
	public ModelAndView getChatViewPage(ModelAndView mv) {
		mv.setViewName("chat");
		
		return mv;
	}
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Open session id: " + session.getId());
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		sessionList.add(session);
	}
	
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for (Session session : WebSocketChat.sessionList) {
				if (!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(message.split(",")[1] + " : " + message);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("Message From " + message.split(",")[1] + ": " + message.split(",")[0]);
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to: " + message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sendAllSessionToMessage(session, message);
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		
	}
	
	@OnClose
	public void onClose(Session session) {
		logger.info("Session: " + session.getId() + " has ended");
		sessionList.remove(session);
	}
}

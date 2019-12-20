package kr.websocket.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	// 방법 1. 일대일 채팅 Map 사용
	Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	// 클라이언트 연결 이후에 실행되는 메서드
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.printf("%s 연결 됨\n", session.getId());
		
		// Map 사용 시
		sessions.put(session.getId(), session);
		
		// List 사용 시
		sessionList.add(session);
		logger.info("{} 연결됨", session.getId());
	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행되는 메서드
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.printf("%s로부터 [%s] 받음\n",
		session.getId(), message.getPayload());
		
		// 연결되어 있는 모든 클라이언트에게 메시지 전송
		session.sendMessage(new TextMessage("echo: " + message.getPayload()));
		
		logger.info("{}로부터 {} 받음", session.getId(), message.getPayload());
		// 배열일 경우 많이 쓸 수 있고 배열이 아닐 경우 최대 2개
//		logger.info("{}로부터 {} 받음", new String[] {session.getId(), message.getPayload()});
		
		for (WebSocketSession websession : sessionList) {
			websession.sendMessage(new TextMessage("echo: " + message.getPayload()));
		}
		
		// Map 사용
//		Iterator<String> sessionIds = sessions.keySet().iterator();
//		String sessionId = "";
//		
//		while (sessionIds.hasNext()) {
//			sessionId = sessionIds.next();
//			sessions.get(sessionId).sendMessage(new TextMessage("echo: " + message.getPayload()));
//		}
	}

	// 클라이언트가 연결을 끊었을 때 실행되는 메서드
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.printf("%s 연결 끊김\n", session.getId());
		sessionList.remove(session);
	}
}


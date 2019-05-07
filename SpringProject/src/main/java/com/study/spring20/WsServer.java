package com.study.spring20;
import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/websocketendpoint2")
public class WsServer {
	private static final java.util.Set<Session> sessions = 
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id" + session.getId());
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("");
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("session : " + session.getId() + " has ended");
		sessions.remove(session);
	}
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + " : " + message);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sendAllSessionToMessage(session, message);
		System.out.println("성공");
	}
	
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for(Session session : WsServer.sessions) {
				if(!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Throwable e, Session session) {
		
	}
}


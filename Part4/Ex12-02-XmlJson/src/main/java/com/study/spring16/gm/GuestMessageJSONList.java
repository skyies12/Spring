package com.study.spring16.gm;

import java.util.List;

public class GuestMessageJSONList {

	private List<GuestMessage> messages;
	
	public GuestMessageJSONList(List<GuestMessage> messages) {
		this.messages = messages;
	}
	
	public List<GuestMessage> getMessages() {
		return messages;
	}
}

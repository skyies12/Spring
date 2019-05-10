package com.study.spring16.gm;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuestMessageController {

	@RequestMapping(value = "/list.xml")
	@ResponseBody
	public GuestMessageXMLList listXml() {
		return getMessageXmlList();
	}
	
	private GuestMessageXMLList getMessageXmlList() {
		List<GuestMessage> messages = Arrays.asList(
				new GuestMessage(1, "홍길동", new Date()),
				new GuestMessage(2, "전우치", new Date())
				);
		
		return new GuestMessageXMLList(messages);
	}
	
	@RequestMapping(value = "/list.json")
	@ResponseBody
	public GuestMessageJSONList listJsonList() {
		return getMessageJSONList();
	}
	
	private GuestMessageJSONList getMessageJSONList() {
		List<GuestMessage> messages = Arrays.asList(
				new GuestMessage(1, "메세지", new Date()),
				new GuestMessage(2, "메세지2", new Date())
				);
		
		return new GuestMessageJSONList(messages);
	}
}

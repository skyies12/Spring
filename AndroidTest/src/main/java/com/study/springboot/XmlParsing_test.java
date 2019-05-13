package com.study.springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParsing_test {

    // tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}

	public static void main(String[] args) {
		int page = 1;	// 페이지 초기값 
		
		ArrayList<DogDto> doglist = new ArrayList<DogDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String uurl = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "scott";
		String upw = "tiger";
		
		try{
			while(true){
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20140301&endde=20190430&pageNo=1&numOfRows=10&ServiceKey=jxdNXHd7MviV0OG96kcvMLDsmrrUH4VJcJ5gizCALgi1jMmyz5tA4sJ3PCByGqHd381IPz3UCtTCnGuX0dOZgQ%3D%3D&pageNo="+page;
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
				System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eElement = (Element) nNode;
						System.out.println("######################");
						//System.out.println(eElement.getTextContent());
						
						Class.forName(driver);
						con = DriverManager.getConnection(uurl, uid, upw);
						String sql = "insert into dog (kindCd, colorCd, age, weight, noticeNo) values(?, ?, ?, ?, ?)";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, getTagValue("kindCd", eElement));
						pstmt.setString(2, getTagValue("colorCd", eElement));
						pstmt.setString(3, getTagValue("age", eElement));
						pstmt.setString(4, getTagValue("weight", eElement));
						pstmt.setString(5, getTagValue("noticeNo", eElement));
						int updateCount = pstmt.executeUpdate();
						
						if(updateCount == 1) {
							System.out.println("성공");
						} else {
							System.out.println("실패");
						}
//						
//						System.out.println("품종  : " + getTagValue("kindCd", eElement));
//						System.out.println("색상  : " + getTagValue("colorCd", eElement));
//						System.out.println("나이 : " + getTagValue("age", eElement));
//						System.out.println("무게  : " + getTagValue("weight", eElement));
//						System.out.println("번호  : " + getTagValue("noticeNo", eElement));
						
						
						
					}	// for end
				}	// if end
				
				page += 1;
				System.out.println("page number : "+page);
				if(page > 3){	
					break;
				}
			}	// while end
			
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
	}	// main end
}	// class end



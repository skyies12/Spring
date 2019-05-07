package com.study.spring20;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/nmt")
public class nmt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public nmt() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String original_str = (String)request.getParameter("test");
		String langCode = (String)request.getParameter("langCode");
		System.out.println(original_str);
		System.out.println(langCode);
		
		PrintWriter out = response.getWriter();
		out.print((String)nmt(original_str, langCode));
		
	}
	public String nmt(String original_str, String langCode) {
		 String clientId = "XmTYRdA0tfG2FwweST4P";//애플리케이션 클라이언트 아이디값";
	     String clientSecret = "RX01m2a1T1";//애플리케이션 클라이언트 시크릿값";
	     
	     String result_text = "";
	     try {
	            String text = URLEncoder.encode(original_str, "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            // post request
	            System.out.println(langCode);
	            String postParams = "source="+ langCode +"&target=ko&text=" + text;
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            wr.close();
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            
	            result_text = response.toString();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		return result_text;
		}
	}
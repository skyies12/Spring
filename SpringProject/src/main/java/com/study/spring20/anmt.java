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


@WebServlet("/anmt")
public class anmt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public anmt() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String original_str = (String)request.getParameter("original_str");

		PrintWriter out = response.getWriter();
		out.print((String)anmt(original_str));
		
	}
		public String anmt(String original_str) {
			String clientId = "k4eqZKvUi2Kxf0PKdVTR";//애플리케이션 클라이언트 아이디값";
			String clientSecret = "BE_kbUQxmK";//애플리케이션 클라이언트 시크릿값";
	     
	     String result_text1 = "";
	     try {
	            String query = URLEncoder.encode(original_str, "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/papago/detectLangs";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            // post request
	            String postParams = "query=" + query;
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
	            //System.out.println(response.toString());
	            result_text1 = response.toString();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	     return result_text1;
	}
	

}

package com.gcs.firewall.interceptors;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gcs.firewall.exceptions.CustomException;

@SuppressWarnings("deprecation")
public class BasicAuthInterceptor extends HandlerInterceptorAdapter{


	public ResultSet rs;
	
	public ResultSet rs1;
	
	public Statement stmt;
	
	public Statement st;
	
	public static String GLOBAL_PERMISSIONS;
	
	public static String GLOBAL_ACCOUNT;
	
	public static String TARGET;
	
	public static String route;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String meth = request.getMethod();
		route = request.getRequestURI();
		if(!meth.equals("OPTIONS")) {
			String authHeader = request.getHeader("Authorization");
			if (authHeader !=null && authHeader !="undefined" && authHeader.toLowerCase().startsWith("basic")) {
			    String base64Credentials = authHeader.substring("Basic".length()).trim();
			    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			    //credentials = username:password
			    TARGET = credentials.split(":")[0];
			    String key = credentials.split(":")[1];
			    if(TARGET.equals("front")) {
			    	if(checkAppKeyExistance(key)) {
			    		return true;
			    	}else {
						return false;

			    	}
			    }
			    else if(TARGET.equals("user")) {
					String res = checkUserKeyExistance(key);
					if(!res.equals("")) {
						return true;
						
					}else {
						return false;	
					}
			    }else if(TARGET.equals("other")) {
							String res = checkKeyExistance(key);
							if(!res.equals("")) {
								return true;
			
							}else {
								throw new CustomException("Something went wrong !");
							}
					}
					else {
						throw new CustomException("Invalid username or password !");
					}
			}
			throw new CustomException("Invalid username or password !");
		}else {
			return true;
		}
	}
	
	
	public String checkUserKeyExistance(String apikey){
		String res ="";
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firewall_module","root","");
			stmt=con.createStatement(); 
			rs=stmt.executeQuery("SELECT id,account_id FROM firewall_users WHERE status IN (1,2,3) AND key_value ='"+apikey+"'");  
			if(rs.next()) {
				GLOBAL_ACCOUNT = rs.getString(2);
				st=con.createStatement();
				rs1=st.executeQuery("SELECT * FROM firewall_users_permissions WHERE status IN (1,2,3) AND user_id ="+rs.getInt(1));  
				if(rs1.next()) {
					GLOBAL_PERMISSIONS = rs1.getString(2);
					res = GLOBAL_ACCOUNT+","+GLOBAL_PERMISSIONS;
					return res;
				}
			}
			
		}catch(Exception e){ 
		System.out.println(route+" "+"1"+e);
	}
		return res;
	}
	
	
	public boolean checkAppKeyExistance(String apikey){
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firewall_module","root","");
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("SELECT key_value FROM firewall_app_api_keys WHERE key_value ='"+apikey+"'");  
			if(rs.next()) {
				return true;
			}
		}catch(Exception e){ System.out.println(route+" "+"2"+e);}
		return false;  
	}
	
	public String checkKeyExistance(String apikey){
		String res="";
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firewall_module","root",""); 
			stmt=con.createStatement();  
			rs=stmt.executeQuery("SELECT * FROM firewall_api_keys WHERE key_value ='"+apikey+"' AND status IN (1,2,3)");  
			if(rs.next())  {
				GLOBAL_ACCOUNT = rs.getString(7);
				st=con.createStatement();
				rs1=st.executeQuery("SELECT * FROM firewall_api_keys_permissions WHERE api_key_id ='"+rs.getInt(1)+"' AND status IN (1,2,3)"); 
				if(rs1.next()) {
					GLOBAL_PERMISSIONS = rs1.getString(3);
					res= GLOBAL_ACCOUNT+","+GLOBAL_PERMISSIONS;
					return res;
				}
			}
		}catch(Exception e){ System.out.println(route+" "+"3"+e);}
		return res;  
	}
}

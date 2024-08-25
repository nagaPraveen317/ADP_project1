package com.example.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.JWTutilityies.JWTHelper;
import com.example.Logs.ApiLogger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
@Order(1)
public class AuthFilter implements Filter{

	//public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private String auth_scope = "com.webage.auth.apis";
	private String api_scope = "com.webage.data.apis";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// get authorization header
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		
		
		if(    !uri.equals("/api/customers")) {
			chain.doFilter(request, response);
			return;			
		}else{
			
			String authheader = req.getHeader("authorization");
			if(authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
				String jwt_token = authheader.substring(7, authheader.length());
				if(JWTHelper.verifyToken(jwt_token)) {
					String request_scopes = JWTHelper.getScopes(jwt_token);
					if(request_scopes.contains(api_scope) || request_scopes.contains(auth_scope)) {
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}		
		// continue
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");

	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApiLogger.log("AuthFilter.init");
		
	}

	@Override
	public void destroy() {
		ApiLogger.log("AuthFilter.destroy");	
	}

	
	
}

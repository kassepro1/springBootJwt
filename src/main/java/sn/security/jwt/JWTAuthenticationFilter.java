package sn.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sn.security.entities.AppUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AppUser appUser=null;
		try {
			 appUser = new ObjectMapper().readValue(request.getInputStream(),AppUser.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		System.out.println("***************************");
		System.out.println("Username :"+appUser.getUsername());
		System.out.println("Password :"+appUser.getPassword());
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
	}
 @Override
protected void successfulAuthentication(HttpServletRequest request, 
		HttpServletResponse response,
		FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
	 	User springUser = (User) authResult.getPrincipal(); 
	 	
	 	String jwt = Jwts.builder()
	 			.setSubject(springUser.getUsername())
	 			.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
	 			.claim("roles",springUser.getAuthorities())
				.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
				.compact();
	 	response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);
		 Map<String,String> token = new HashMap<>();
		 token.put("token",SecurityConstants.TOKEN_PREFIX+jwt);
		 response.setContentType("application/json");
		 new ObjectMapper().writeValue(response.getOutputStream(),token);
}
}

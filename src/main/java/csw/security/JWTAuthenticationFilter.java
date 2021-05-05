package csw.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import csw.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * class to validate if exists an JWT Token on the requests
 * 
 * @author eduardo
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements SecurityConstants {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/api/usuarios/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);		
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsuario(), creds.getSenha(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// PARA POR DEVOLTA EXPIRACAO DO TOKEN - > setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsuario())
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}	
	
}
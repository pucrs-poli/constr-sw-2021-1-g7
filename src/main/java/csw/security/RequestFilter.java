package csw.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import csw.service.logic.StudentService;

@Component("RequestFilter")
public class RequestFilter extends GenericFilterBean {

	private static Log log = LogFactory.getLog(RequestFilter.class);
	
	@Autowired
	private StudentService studentService;

	private static final String INVALID_TOKEN = "Permission token not founded or invalid.";

	public RequestFilter() {
		super();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		String path = httpReq.getRequestURI();
		if (isPublicEndPoint(path)) {
			chain.doFilter(req, res);
		} else {
			validateActiveSession(httpReq, req, res, chain);
		}
	}

	private boolean isPublicEndPoint(String path) {
	   return true;
	//	return path.startsWith("/api/users/auth");
	}

	public void validateActiveSession(HttpServletRequest httpReq, ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		String token = httpReq.getHeader(Constants.AUTHORIZATION);
//		Integer httpStatus = userService.getValidateToken(token);
//		if (null != httpStatus && httpStatus == 200) {
			chain.doFilter(httpReq, res);
//		} else {
//			log.info("RequestFilter.doFilter() | token == null || getValidateToken == null");
//			throw new ServletException(INVALID_TOKEN);
//		}
	}
}

package csw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import csw.dto.HttpResponseDTO;
import csw.dto.LoginDTO;
import csw.dto.PasswordDTO;
import csw.dto.UserRepresentationDTO;
import csw.security.Constants;
import csw.service.logic.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> registerUser(HttpServletRequest request, @RequestBody UserRepresentationDTO user) throws Exception {
		HttpResponseDTO response = userService.registerUser(request.getHeader(Constants.AUTHORIZATION), user);
		return super.response(response, response.getStatus());
	}	

	@RequestMapping(value="/auth",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> authUser(@RequestBody LoginDTO login) throws Exception {
		HttpResponseDTO response = userService.authUser(login);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> updateUser(HttpServletRequest request, @RequestBody UserRepresentationDTO user, @PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = userService.updateUser(request.getHeader(Constants.AUTHORIZATION), user, id);
		return super.response(response, response.getStatus());
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> patchUser(HttpServletRequest request, @RequestBody PasswordDTO cred, @PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = userService.patchUser(request.getHeader(Constants.AUTHORIZATION), cred, id);
		return super.response(response, response.getStatus());
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> deleteUser(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = userService.deleteUser(request.getHeader(Constants.AUTHORIZATION), id);
		return super.response(response, response.getStatus());
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getUser(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
		return super.response(userService.getUser(request.getHeader(Constants.AUTHORIZATION), id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> listUsers(HttpServletRequest request) throws Exception {
		return super.response(userService.listUsers(request.getHeader(Constants.AUTHORIZATION)), HttpStatus.OK);
	}
}

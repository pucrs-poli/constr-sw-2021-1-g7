package csw.controller;

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
import csw.dto.UserDTO;
import csw.service.logic.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> registerUser(@RequestBody UserDTO user) throws Exception {
		return super.response(userService.registerUser(user), HttpStatus.OK);
	}	

	@RequestMapping(value="/auth",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> authUser(@RequestBody LoginDTO login) throws Exception {
		HttpResponseDTO response = userService.authUser(login);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> updateUser(@RequestBody UserDTO user, @PathVariable("id") String id) throws Exception {
		return super.response(userService.updateUser(user, id), HttpStatus.OK);
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> patchUser(@RequestBody PasswordDTO cred, @PathVariable("id") String id) throws Exception {
		return super.response(userService.patchUser(cred, id), HttpStatus.OK);
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> deleteUser(@PathVariable("id") String id) throws Exception {
		return super.response(userService.deleteUser(id), HttpStatus.OK);
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getUser(@PathVariable("id") String id) throws Exception {
		return super.response(userService.getUser(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> listUsers() throws Exception {
		return super.response(userService.listUsers(), HttpStatus.OK);
	}
}

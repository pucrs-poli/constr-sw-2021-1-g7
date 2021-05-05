package csw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csw.service.logic.UserService;

@RestController
@RequestMapping("/api/usuario")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;
//
//	/**
//	 * Registrar novo usuário.
//	 * 
//	 * @return {@link ResponseEntity<HttpResponseDTO>}
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<HttpResponseDTO> signUp(@RequestBody User user) throws Exception {
//		return super.response(userService.registerUser(user), HttpStatus.OK);
//	}
//
//	/**
//	 * Buscar um usuário pelo login.
//	 * 
//	 * @return {@link ResponseEntity<HttpResponseDTO>}
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<HttpResponseDTO> findUser(@RequestParam String usuario) throws Exception {
//		return super.response(userService.getUser(usuario), HttpStatus.OK);
//	}

}

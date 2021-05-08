package csw.service.logic;

import org.springframework.stereotype.Service;

import csw.dto.HttpResponseDTO;
import csw.dto.LoginDTO;
import csw.dto.PasswordDTO;
import csw.dto.UserDTO;

/**
 * class that have method related to services concerning users
 * 
 * @author Eduardo Dornelles
 */
@Service
public class UserService extends AbstractService {


	public HttpResponseDTO registerUser(final UserDTO user) {
		this.LogServiceConsumed(this.getClassName(), "registerUser");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro Keycloak

		return response;
	}
	
	public HttpResponseDTO updateUser(final UserDTO user, final String id) {
		this.LogServiceConsumed(this.getClassName(), "updateUser");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro Keycloak

		return response;
	}
	
	public HttpResponseDTO deleteUser(final String id) {
		this.LogServiceConsumed(this.getClassName(), "deleteUser");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro Keycloak

		return response;
	}

	public HttpResponseDTO authUser(final LoginDTO user) {
		this.LogServiceConsumed(this.getClassName(), "authUser");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro keycloak
		return response;
	}

	/**
	 * RESETAR A SENHA SOMENTE
	 */
	public HttpResponseDTO patchUser(PasswordDTO cred, String id) {
		this.LogServiceConsumed(this.getClassName(), "patchUser");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro keycloak
		return response;
	}

	public HttpResponseDTO listUsers() {
		this.LogServiceConsumed(this.getClassName(), "listUsers");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro keycloak
		return response;
	}

	public HttpResponseDTO getUser(String id) {
		this.LogServiceConsumed(this.getClassName(), "getUser");
		HttpResponseDTO response = new HttpResponseDTO();
		//TODO: Chamada pro keycloak
		return response;
	}

}

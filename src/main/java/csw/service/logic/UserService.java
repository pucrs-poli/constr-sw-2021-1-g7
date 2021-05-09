package csw.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import csw.domain.Messages;
import csw.dto.HttpResponseDTO;
import csw.dto.LoginDTO;
import csw.dto.PasswordDTO;
import csw.dto.TokenDTO;
import csw.dto.UserDTO;
import csw.service.consumer.UserServiceConsumer;

@Service
public class UserService extends AbstractService {

	@Autowired
	UserServiceConsumer userServiceConsumer;
	
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
		TokenDTO token = userServiceConsumer.requestAuth(user);
		if (token != null)
			return HttpResponseDTO.success("access_token", token.getAccess_token());
		else
			return HttpResponseDTO.fail(Messages.A001, "Usuário ou senha inválido(s).", HttpStatus.UNAUTHORIZED);
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

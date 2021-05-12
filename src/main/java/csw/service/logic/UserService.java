package csw.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import csw.domain.Messages;
import csw.dto.HttpResponseDTO;
import csw.dto.LoginDTO;
import csw.dto.PasswordDTO;
import csw.dto.TokenDTO;
import csw.dto.UserRepresentationDTO;
import csw.dto.ValidateTokenDTO;
import csw.service.consumer.UserServiceConsumer;

@Service
public class UserService extends AbstractService {

	@Autowired
	UserServiceConsumer userServiceConsumer;
	
	public HttpResponseDTO registerUser(final String token, final UserRepresentationDTO user) {
		this.LogServiceConsumed(this.getClassName(), "registerUser");
		Integer statusCode = this.userServiceConsumer.requestRegisterUser(token, user);
		return getResponseCode(statusCode);
	}


	public HttpResponseDTO updateUser(final String token, final UserRepresentationDTO user, final String id) {
		this.LogServiceConsumed(this.getClassName(), "updateUser");
		Integer statusCode = this.userServiceConsumer.requestUpdateUser(token, user, id);
		return getResponseCode(statusCode);
	}

	public HttpResponseDTO deleteUser(final String token, final String id) {
		this.LogServiceConsumed(this.getClassName(), "deleteUser");
		Integer statusCode = this.userServiceConsumer.requestDeleteUser(token, id);
		return getResponseCode(statusCode);
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
	 * @param token 
	 */
	public HttpResponseDTO patchUser(String token, PasswordDTO cred, String id) {
		this.LogServiceConsumed(this.getClassName(), "patchUser");
		Integer statusCode = this.userServiceConsumer.requestResetPasswordUser(token, cred, id);
		return getResponseCode(statusCode);
	}

	public HttpResponseDTO listUsers(String token) {
		this.LogServiceConsumed(this.getClassName(), "listUsers");
		List<UserRepresentationDTO> list = this.userServiceConsumer.requestListUsers(token);
		return HttpResponseDTO.success("users", list);
	}

	public HttpResponseDTO getUser(String token, String id) {
		this.LogServiceConsumed(this.getClassName(), "getUser");
		UserRepresentationDTO user = this.userServiceConsumer.requestUser(token, id);
		return HttpResponseDTO.success("user", user);
	}

	private HttpResponseDTO getResponseCode(Integer statusCode) {
		if (statusCode != null && statusCode >= 200 && statusCode <= 299) {
			return HttpResponseDTO.success(HttpStatus.valueOf(statusCode));
		} else {
			return HttpResponseDTO.fail(HttpStatus.valueOf(statusCode == null ? 400 : statusCode));
		}
	}
	
	public Integer getValidateToken(String token) {
		return this.userServiceConsumer.requestValidateToken(token);
	}

}

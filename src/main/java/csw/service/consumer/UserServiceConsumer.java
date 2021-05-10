package csw.service.consumer;

import java.util.HashMap;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import csw.dto.LoginDTO;
import csw.dto.PasswordDTO;
import csw.dto.TokenDTO;
import csw.dto.UserRepresentationDTO;
import csw.utility.Address;

@Service
public class UserServiceConsumer extends AbstractServiceConsumer {

	@Value("${keycloak.url.auth}")
	private String auth_endpoint;

	public TokenDTO requestAuth(LoginDTO user) {
		try {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("client_id", user.getClient_id());
			map.add("username", user.getUsername());
			map.add("password", user.getPassword());
			map.add("grant_type", user.getGrant_type());
			HttpEntity<TokenDTO> response = super.post(new ParameterizedTypeReference<TokenDTO>() {
			}, auth_endpoint, map, Address.AUTH_USER, null, MediaType.APPLICATION_FORM_URLENCODED);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<UserRepresentationDTO> requestListUsers(String token) {
		try {
			List<UserRepresentationDTO> response = super.get(
					new ParameterizedTypeReference<List<UserRepresentationDTO>>() {
					}, "/admin/realms/CswAutenticacao/users", new HashMap<>(), Address.AUTH_USER, token);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserRepresentationDTO requestUser(String token, String id) {
		try {
			UserRepresentationDTO response = super.get(new ParameterizedTypeReference<UserRepresentationDTO>() {
			}, "/admin/realms/CswAutenticacao/users/" + id, new HashMap<>(), Address.AUTH_USER, token);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer requestRegisterUser(String token, UserRepresentationDTO user) {
		try {
			return super.postStatus(new ParameterizedTypeReference<Object>() {
			}, "/admin/realms/CswAutenticacao/users/", user, Address.AUTH_USER, token, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer requestUpdateUser(String token, UserRepresentationDTO user, String id) {
		try {
			return super.putStatus(new ParameterizedTypeReference<Object>() {
			}, "/admin/realms/CswAutenticacao/users/" + id, user, Address.AUTH_USER, token, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer requestResetPasswordUser(String token, PasswordDTO pwd, String id) {
		try {
			return super.putStatus(new ParameterizedTypeReference<Object>() {
			}, "/admin/realms/CswAutenticacao/users/" + id + "/reset-password", pwd, Address.AUTH_USER, token, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer requestDeleteUser(String token, String id) {
		try {
			return super.deleteStatus(Response.class, "/admin/realms/CswAutenticacao/users/" + id, Address.AUTH_USER, token, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

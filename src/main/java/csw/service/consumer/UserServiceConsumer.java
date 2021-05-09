package csw.service.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import csw.dto.LoginDTO;
import csw.dto.TokenDTO;
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
			//text/html;charset=utf-8
			HttpEntity<TokenDTO> response = super.post(new ParameterizedTypeReference<TokenDTO>() {
            }, auth_endpoint, map, Address.AUTH_USER, null, MediaType.APPLICATION_FORM_URLENCODED);
			return response.getBody();
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}

	}

}

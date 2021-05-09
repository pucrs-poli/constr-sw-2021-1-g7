package csw.dto;

public class TokenDTO {
	private String access_token;

	public TokenDTO() {
		super();
	}

	public TokenDTO(String access_token) {
		super();
		this.access_token = access_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}

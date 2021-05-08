package csw.dto;

public class PasswordDTO {

	private String id;
	private String type;
	private String userLabel;
	private Long createdDate;
	private String secretData;
	private String credentialData;
	private Integer priority;
	private String value;
	protected Boolean temporary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getSecretData() {
		return secretData;
	}

	public void setSecretData(String secretData) {
		this.secretData = secretData;
	}

	public String getCredentialData() {
		return credentialData;
	}

	public void setCredentialData(String credentialData) {
		this.credentialData = credentialData;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getTemporary() {
		return temporary;
	}

	public void setTemporary(Boolean temporary) {
		this.temporary = temporary;
	}

}

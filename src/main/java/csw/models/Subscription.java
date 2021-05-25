package csw.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class Subscription {

	@Id
	private String idSubscription;
	private Long code;
	private String idStudent;
	private String edition; // TODO: vai vir do grupo 5
	private String tests; // TODO vai vir do grupo 8

	public Subscription() {
		super();
	}

	public String getIdSubscription() {
		return idSubscription;
	}

	public void setIdSubscription(String idSubscription) {
		this.idSubscription = idSubscription;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

}

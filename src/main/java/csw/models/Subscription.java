package csw.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class Subscription {

	@Id
	private String idSubscription;
	private Long code;
	private String idStudent;
	private String edition;
	private List<String> tests;

	public Subscription() {
		super();
	}

	public Subscription(Long code, String idStudent, String edition, List<String> tests) {
		super();
		this.code = code;
		this.idStudent = idStudent;
		this.edition = edition;
		this.tests = tests;
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

	public List<String> getTests() {
		return tests;
	}

	public void setTests(List<String> tests) {
		this.tests = tests;
	}

}

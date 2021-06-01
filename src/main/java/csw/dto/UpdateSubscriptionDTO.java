package csw.dto;

import java.time.LocalDate;
import java.util.List;

public class UpdateSubscriptionDTO {

	private String idSubscription;
	private Long code;
	private String idStudent;
	private String edition;
	private List<String> tests;
	
	public UpdateSubscriptionDTO(String idSubscription, Long code, String idStudent, String edition, List<String> tests) {
		super();
		this.idSubscription = idSubscription;
		this.code = code;
		this.idStudent = idStudent;
		this.edition = edition;
		this.tests = tests;
	}

	public UpdateSubscriptionDTO() {
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

	public List<String> getTests() {
		return tests;
	}

	public void setTests(List<String> tests) {
		this.tests = tests;
	}

}

package csw.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {

	@Id
	private String idStudent;
	// @CPF
	private String cpf;
	private String name;
	private LocalDate birthDate;
	private String address;
	// private List<Subscription> subscriptions;

	public Student() {
		super();
	}

	public Student(String cpf, String name, LocalDate birthDate, String address) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
	}

	public String getId() {
		return idStudent;
	}

	public void setId(String id) {
		this.idStudent = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public List<Subscription> getSubscriptions() {
//		return subscriptions;
//	}
//
//	public void setSubscriptions(List<Subscription> subscriptions) {
//		this.subscriptions = subscriptions;
//	}

}

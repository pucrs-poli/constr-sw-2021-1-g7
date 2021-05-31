package csw.dto;

import java.time.LocalDate;

public class AddStudentDTO {

	private String cpf;
	private String name;
	private LocalDate birthDate;
	private String address;

	public AddStudentDTO() {
		super();
	}

	public AddStudentDTO(String cpf, String name, LocalDate birthDate, String address) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

}

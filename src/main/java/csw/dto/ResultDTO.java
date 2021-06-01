package csw.dto;

public class ResultDTO {

	private String id;
	private Double nota;
	private String studentId;

	public ResultDTO() {
		super();
	}

	public ResultDTO(String id, Double nota, String studentId) {
		super();
		this.id = id;
		this.nota = nota;
		this.studentId = studentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}

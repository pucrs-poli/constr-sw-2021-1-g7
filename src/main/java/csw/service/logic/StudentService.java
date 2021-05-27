package csw.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import csw.domain.Messages;
import csw.dto.AddStudentDTO;
import csw.dto.EditStudentDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.StudentDTO;
import csw.dto.UpdateStudentDTO;
import csw.models.Student;
import csw.repository.StudentRepository;

@Service
public class StudentService extends AbstractService {

	@Autowired
	StudentRepository studentRepository;

	public HttpResponseDTO registerStudent(AddStudentDTO student) {
		this.LogServiceConsumed(this.getClassName(), "registerStudent");
		Student studentSaved = this.studentRepository.save(super.map(student, Student.class));
		if (studentSaved != null) {
			return HttpResponseDTO.success("student", super.map(studentSaved, StudentDTO.class), HttpStatus.CREATED);
		} else {
			return HttpResponseDTO.fail(Messages.A001, "Erro ao salvar novo estudante.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public HttpResponseDTO deleteStudent(String id) {
		this.LogServiceConsumed(this.getClassName(), "deleteStudent");
		try {
			this.studentRepository.deleteById(id);
			return HttpResponseDTO.success(Messages.A003, "Estudante Removido com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResponseDTO.fail(Messages.A002, "Erro ao remover estudante.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public HttpResponseDTO updateStudent(UpdateStudentDTO student) {
		this.LogServiceConsumed(this.getClassName(), "updateStudent");

		if (this.studentRepository.findById(student.getIdStudent()).isPresent()) {

			Student studentSaved = this.studentRepository.save(super.map(student, Student.class));

			if (studentSaved != null) {
				return HttpResponseDTO.success("student", super.map(studentSaved, StudentDTO.class));
			} else {
				return HttpResponseDTO.fail(Messages.A004, "Erro ao atualizar estudante.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Estudante não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO editStudent(EditStudentDTO student) {
		this.LogServiceConsumed(this.getClassName(), "editStudent");

		if (this.studentRepository.findById(student.getIdStudent()).isPresent()) {

			Student studentSaved = this.studentRepository.save(super.map(student, Student.class));

			if (studentSaved != null) {
				return HttpResponseDTO.success("student", super.map(studentSaved, StudentDTO.class));
			} else {
				return HttpResponseDTO.fail(Messages.A006, "Erro ao editar estudante.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Estudante não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO listStudents() {
		this.LogServiceConsumed(this.getClassName(), "listStudents");
		return HttpResponseDTO.success("students", super.mapAll(this.studentRepository.findAll(), StudentDTO.class));
	}

	public HttpResponseDTO getStudent(String id) {
		this.LogServiceConsumed(this.getClassName(), "getStudent");
		Student std = this.studentRepository.findById(id).orElse(null);
		if (std != null) {
			return HttpResponseDTO.success("student", super.map(std, StudentDTO.class));
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Estudante não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO getStudentByParams(String id, String cpf, String name, String birthDate, String address) {
		this.LogServiceConsumed(this.getClassName(), "getStudentByParams");
		List<Student> std = this.studentRepository.findByIdStudentOrCpfOrNameOrBirthDateOrAddress(id, cpf, name,
				birthDate, address);
		if (std != null) {
			return HttpResponseDTO.success("students", super.mapAll(std, StudentDTO.class));
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Estudante não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO getStudentByParam(String value) {
		this.LogServiceConsumed(this.getClassName(), "getStudentByParam");
		List<Student> std = this.studentRepository.findByParam(value);
		if (std != null) {
			return HttpResponseDTO.success("students", super.mapAll(std, StudentDTO.class));
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Estudante não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

}

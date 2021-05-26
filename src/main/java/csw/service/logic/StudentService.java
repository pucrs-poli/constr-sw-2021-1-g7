package csw.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import csw.domain.Messages;
import csw.dto.AddStudentDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.StudentDTO;
import csw.dto.UpdateStudentDTO;
import csw.models.Student;
import csw.repository.StudentRepository;
import csw.utility.ObjectMapperUtils;

@Service
public class StudentService extends AbstractService {

	@Autowired
	StudentRepository studentRepository;

	public HttpResponseDTO registerStudent(AddStudentDTO student) {
		this.LogServiceConsumed(this.getClassName(), "registerStudent");
		Student studentSaved = this.studentRepository
				.save(ObjectMapperUtils.getInstancia().map(student, Student.class));
		if (studentSaved != null) {
			return HttpResponseDTO.success("student",
					ObjectMapperUtils.getInstancia().map(studentSaved, StudentDTO.class), HttpStatus.CREATED);
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

			Student studentSaved = this.studentRepository
					.save(ObjectMapperUtils.getInstancia().map(student, Student.class));
			
			if (studentSaved != null) {
				return HttpResponseDTO.success("student",
						ObjectMapperUtils.getInstancia().map(studentSaved, StudentDTO.class));
			} else {
				return HttpResponseDTO.fail(Messages.A004, "Erro ao atualizar estudante.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Estudante não encontrado.",
					HttpStatus.NOT_FOUND);
		}
	}

}

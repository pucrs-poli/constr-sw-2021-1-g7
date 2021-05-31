package csw.service.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import csw.dto.AddStudentDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.StudentDTO;
import csw.dto.UpdateStudentDTO;
import csw.models.Student;
import csw.repository.StudentRepository;

@SpringBootTest
public class StudentServiceTests {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	static private ArrayList<Student> students = new ArrayList<Student>();	
	
	private int totalStudents;
	
	@BeforeEach
	void createrUserMocks() {
		Student newStudent = studentRepository
				.save(new Student("93156844444", "Paulo Silva", LocalDate.now(), "Rua dos andradas 456"));
		Student newStudent1 = studentRepository
				.save(new Student("33333333333", "João Paulo", LocalDate.now(), "Rua Irmão josé otão 5453"));
		Student newStudent2 = studentRepository
				.save(new Student("99999999999", "Maria Da Silva", LocalDate.now(), "Avenida Protásio Alves"));
		students.add(newStudent);
		students.add(newStudent1);
		students.add(newStudent2);
		totalStudents = studentRepository.findAll().size();
	}
	
	@AfterEach
	void clearUserMocks() {
		studentRepository.deleteAll(students);
		students = new ArrayList<>();
	}
	
	@Test
	void mustRegisterAStudent() {
		HttpResponseDTO dto = studentService.registerStudent(new AddStudentDTO("11111111111", "Paulo Silva", LocalDate.now(), "Rua dos andradas 456"));
		StudentDTO studentDTO = (StudentDTO) dto.getContent().get("student");
		assertNotEquals(studentDTO.getIdStudent(), null);			
		students.add(studentService.map(studentDTO, Student.class));
	}
	
	@Test
	void mustDeleteStudent() {
		HttpResponseDTO dto = studentService.deleteStudent(students.get(0).getId());
		assertEquals(dto.getSuccess(), Boolean.TRUE);
	}
	
	@Test
	void mustFindAValidStudentById() {
		HttpResponseDTO dto = studentService.getStudent(students.get(0).getId());
		StudentDTO studentDTO = (StudentDTO) dto.getContent().get("student");
		assertEquals(studentDTO.getIdStudent(), students.get(0).getId());	
	}
	
	@Test
	void mustGetAllStudents() {
		HttpResponseDTO dto = studentService.listStudents();
		List<Student> students = (List<Student>) dto.getContent("students");
		assertEquals(students.size() , totalStudents);
	}
	
	@Test
	void mustEditAValidStudent() {
		HttpResponseDTO dto = studentService.updateStudent(new UpdateStudentDTO(students.get(0).getId(), "12312312312", "Nome Diferente", LocalDate.now(), "Rua dos andradas 456"));
		StudentDTO studentDTO = (StudentDTO) dto.getContent().get("student");
		assertEquals(studentDTO.getIdStudent(), students.get(0).getId());	
	}
	
	@Test
	void mustFindAStudentByAValidParam() {
		HttpResponseDTO dto = studentService.getStudentByParam(students.get(1).getId());
		List<StudentDTO> contentDtos = (List<StudentDTO>) dto.getContent().get("students");		
		assertEquals(contentDtos.get(0).getCpf(), students.get(1).getCpf());
	}
	
	@Test
	void mustFindStudentByValidParams() {
		HttpResponseDTO dto = studentService.getStudentByParams(null, students.get(0).getCpf(), students.get(0).getName(), null, null);
		List<StudentDTO> contentDtos = (List<StudentDTO>) dto.getContent().get("students");		
		assertEquals(contentDtos.get(0).getCpf(), students.get(0).getCpf());
		assertEquals(contentDtos.get(0).getName(), students.get(0).getName());
	}
}

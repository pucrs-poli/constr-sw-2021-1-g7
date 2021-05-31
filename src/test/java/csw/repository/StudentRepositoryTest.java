package csw.repository;



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

import csw.models.Student;

@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	private int totalStudents;

	static private ArrayList<Student> students = new ArrayList<Student>();
	
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
	void mustInsertValidStudent() {
		Student newStudent = studentRepository
				.save(new Student("11111111111", "Paulo Silva", LocalDate.now(), "Rua dos andradas 456"));
		assertNotEquals(newStudent, null);
		students.add(newStudent);
	}
	
	@Test
	void mustDeleteValidStudent() {
		Student student = studentRepository.findById(students.get(0).getId()).get();
		assertNotEquals(student, null);
		this.studentRepository.delete(students.get(0));
		assertEquals(studentRepository.existsById(student.getId()), false);
	}
	
	@Test
	void mustFindAValidStudentById() {
		Student student = studentRepository.findById(students.get(0).getId()).get();
		assertNotEquals(student, null);
	}
	
	@Test
	void mustGetAllStudents() {
		List<Student> students = studentRepository.findAll();
		assertEquals(students.size(), totalStudents);
	}
	
	@Test
	void mustEditAValidStudent() {
		Student student = studentRepository.findById(students.get(0).getId()).get();
		student.setName("Nome Teste Editado");
		student.setCpf("22222222222");
		LocalDate date = LocalDate.now();
		student.setBirthDate(date);		
		Student studentEdited = studentRepository.save(student);
		assertNotEquals(studentEdited, null);
		assertEquals(studentEdited.getName(), "Nome Teste Editado");
		assertEquals(studentEdited.getCpf(), "22222222222");
		assertEquals(studentEdited.getBirthDate(), date);
	}
	
	@Test
	void mustFindAValidStudentByAValidParam() {
		Student student = studentRepository.findById(students.get(1).getId()).get();
		List<Student> students = studentRepository.findByParam(student.getCpf());		
		assertEquals(students.get(0).getCpf(), student.getCpf());
	}
	
	@Test
	void mustFindAValidStudentByValidParams() {
		Student student = studentRepository.findById(students.get(1).getId()).get();
		List<Student> students = studentRepository.findByIdStudentOrCpfOrNameOrBirthDateOrAddress(student.getId(), null, student.getName(), null, null);
		assertEquals(students.get(0).getId(), student.getId());
		assertEquals(students.get(0).getName(), student.getName());
	}


}

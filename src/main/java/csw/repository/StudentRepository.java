package csw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import csw.models.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
	
	List<Student> findByIdStudentOrCpfOrNameOrBirthDateOrAddress(String id, String cpf, String name, String birthDate, String address);
	
	@Query("{ $or: [{'idStudent' : ?0}, {'cpf' : ?0}, {'name' : ?0}, {'birthDate' : ?0}, {'address' : ?0}]}")
	List<Student> findByParam(String param);
}
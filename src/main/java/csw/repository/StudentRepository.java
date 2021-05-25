package csw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import csw.models.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}
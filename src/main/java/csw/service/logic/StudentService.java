package csw.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csw.models.Student;
import csw.repository.StudentRepository;

@Service
public class StudentService extends AbstractService {

	@Autowired
	StudentRepository studentRepository;
	
    public List<Student> findAll() {
        return studentRepository.findAll();
     }
}

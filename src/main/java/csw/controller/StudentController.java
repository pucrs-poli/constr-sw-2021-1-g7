package csw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csw.service.logic.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController extends AbstractController {

	@Autowired
	private StudentService studentService;

}

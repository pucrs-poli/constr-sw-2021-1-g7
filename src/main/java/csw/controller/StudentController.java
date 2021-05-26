package csw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import csw.dto.AddStudentDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.UpdateStudentDTO;
import csw.security.Constants;
import csw.service.logic.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController extends AbstractController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> registerUser(HttpServletRequest request, @RequestBody AddStudentDTO student) throws Exception {
		HttpResponseDTO response = studentService.registerStudent(student);
		return super.response(response, response.getStatus());
	}	
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> deleteStudent(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = studentService.deleteStudent(id);
		return super.response(response, response.getStatus());
	}	
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> updateStudent(@RequestBody UpdateStudentDTO student) throws Exception {
		HttpResponseDTO response = studentService.updateStudent(student);
		return super.response(response, response.getStatus());
	}	

}

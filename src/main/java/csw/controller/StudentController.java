package csw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import csw.dto.AddStudentDTO;
import csw.dto.EditStudentDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.UpdateStudentDTO;
import csw.service.logic.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController extends AbstractController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> registerUser(@RequestBody AddStudentDTO student) throws Exception {
		HttpResponseDTO response = studentService.registerStudent(student);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> deleteStudent(@PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = studentService.deleteStudent(id);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> updateStudent(@RequestBody UpdateStudentDTO student) throws Exception {
		HttpResponseDTO response = studentService.updateStudent(student);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> editStudent(@RequestBody EditStudentDTO student) throws Exception {
		HttpResponseDTO response = studentService.editStudent(student);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> listStudents() throws Exception {
		return super.response(studentService.listStudents(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getStudent(@PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = studentService.getStudent(id);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(path = "/query/complex", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getStudentByParams(@RequestParam(required = false) String id,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String name,
			@RequestParam(required = false) String birthDate, @RequestParam(required = false) String address)
			throws Exception {
		HttpResponseDTO response = studentService.getStudentByParams(id, cpf, name, birthDate, address);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(path = "/query/simple", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getStudentByParam(String value) throws Exception {
		HttpResponseDTO response = studentService.getStudentByParam(value);
		return super.response(response, response.getStatus());
	}
}

package csw.controller;



import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import csw.dto.AddStudentDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.StudentDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;	    
    
    @PostConstruct
    void init() {
    	objectMapper.registerModule(new JavaTimeModule());
    }

	static private ArrayList<StudentDTO> students = new ArrayList<StudentDTO>();
   
    @Test
    @Order(0)
    public void mustCallListAllStudents() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/api/students")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @Order(1)
    public void mustCallRegisterStudent() throws Exception {
    	ResultActions rst = mvc.perform(MockMvcRequestBuilders.post("/api/students")
    			.content(objectMapper.writeValueAsString(new AddStudentDTO("93156844444", "Student Teste", LocalDate.now(), "Rua dos testes 123456")))
    			.accept(MediaType.APPLICATION_JSON)
    		    .contentType(MediaType.APPLICATION_JSON))    	
    		    .andExpect(MockMvcResultMatchers.status().isCreated());   
    	MvcResult result = rst.andReturn();
    	objectMapper.registerModule(new JavaTimeModule());
    	Type typeMyType = new TypeToken<HttpResponseDTO>(){}.getType();
    	HttpResponseDTO response = new Gson().fromJson(result.getResponse().getContentAsString(), typeMyType);
    	StudentDTO student = objectMapper.convertValue(response.getContent("student"), new TypeReference<StudentDTO>() {});
    	students.add(student);    	
    }
    
    @Test
    @Order(2)
    public void mustCallRemoveStudentById() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.delete("/api/students/"+students.get(0).getIdStudent())
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
}

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

import csw.dto.AddSubscriptionDTO;
import csw.dto.EditSubscriptionDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.SubscriptionDTO;
import csw.dto.UpdateSubscriptionDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;	    
    
    @PostConstruct
    void init() {
    	objectMapper.registerModule(new JavaTimeModule());
    }

	static private ArrayList<SubscriptionDTO> subscriptions = new ArrayList<SubscriptionDTO>();
   
    @Test
    @Order(0)
    public void mustCallListAllSubscriptions() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/api/subscriptions")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @Order(1)
    public void mustCallRegisterSubscription() throws Exception {
    	ResultActions rst = mvc.perform(MockMvcRequestBuilders.post("/api/subscriptions")
    			.content(objectMapper.writeValueAsString(new AddSubscriptionDTO((long) 1111111, "1", "Edição teste", new ArrayList<>())))
    			.accept(MediaType.APPLICATION_JSON)
    		    .contentType(MediaType.APPLICATION_JSON))    	
    		    .andExpect(MockMvcResultMatchers.status().isCreated());   
    	MvcResult result = rst.andReturn();
    	objectMapper.registerModule(new JavaTimeModule());
    	Type typeMyType = new TypeToken<HttpResponseDTO>(){}.getType();
    	HttpResponseDTO response = new Gson().fromJson(result.getResponse().getContentAsString(), typeMyType);
    	SubscriptionDTO subscription = objectMapper.convertValue(response.getContent("subscription"), new TypeReference<SubscriptionDTO>() {});
    	subscriptions.add(subscription);    	
    }
    
    @Test
    @Order(2)
    public void mustCallSubscriptionById() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/api/subscriptions/"+subscriptions.get(0).getIdSubscription())
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @Order(3)
    public void mustCallResultBySubscriptionId() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/api/subscriptions/"+ subscriptions.get(0).getIdSubscription() +"/results")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @Order(4)
    public void mustCallUpdateSubscription() throws Exception {
    	ResultActions rst = mvc.perform(MockMvcRequestBuilders.put("/api/subscriptions")
    			.content(objectMapper.writeValueAsString(new UpdateSubscriptionDTO(subscriptions.get(0).getIdSubscription(),(long) 4324234, "1", "Subscription updated", new ArrayList<>())))
    			.accept(MediaType.APPLICATION_JSON)
    		    .contentType(MediaType.APPLICATION_JSON))    	
    		    .andExpect(MockMvcResultMatchers.status().isOk());   
    	MvcResult result = rst.andReturn();
    	objectMapper.registerModule(new JavaTimeModule());
    	Type typeMyType = new TypeToken<HttpResponseDTO>(){}.getType();
    	HttpResponseDTO response = new Gson().fromJson(result.getResponse().getContentAsString(), typeMyType);
    	SubscriptionDTO subscription = objectMapper.convertValue(response.getContent("subscription"), new TypeReference<SubscriptionDTO>() {});
    	subscriptions.set(0,subscription);
    }
    
    @Test
    @Order(5)
    public void mustCallEditSubscription() throws Exception {
    	ResultActions rst = mvc.perform(MockMvcRequestBuilders.patch("/api/subscriptions")
    			.content(objectMapper.writeValueAsString(new EditSubscriptionDTO(subscriptions.get(0).getIdSubscription(), (long) 9999999, "1", "Subscription edited", new ArrayList<>())))
    			.accept(MediaType.APPLICATION_JSON)
    		    .contentType(MediaType.APPLICATION_JSON))    	
    		    .andExpect(MockMvcResultMatchers.status().isOk());   
    	MvcResult result = rst.andReturn();
    	objectMapper.registerModule(new JavaTimeModule());
    	Type typeMyType = new TypeToken<HttpResponseDTO>(){}.getType();
    	HttpResponseDTO response = new Gson().fromJson(result.getResponse().getContentAsString(), typeMyType);
    	SubscriptionDTO subscription = objectMapper.convertValue(response.getContent("subscription"), new TypeReference<SubscriptionDTO>() {});
    	subscriptions.set(0,subscription);
    }
    
    @Test
    @Order(6)
    public void mustCallRemoveSubscriptionById() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.delete("/api/subscriptions/"+subscriptions.get(0).getIdSubscription())
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
}

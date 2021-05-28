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

import csw.dto.AddSubscriptionDTO;
import csw.dto.EditSubscriptionDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.UpdateSubscriptionDTO;
import csw.service.logic.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController extends AbstractController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> registerSubscription(@RequestBody AddSubscriptionDTO subscription) throws Exception {
		HttpResponseDTO response = subscriptionService.registerSubscription(subscription);
		return super.response(response, response.getStatus());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> deleteSubscription(@PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = subscriptionService.deleteSubscription(id);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> updateSubscription(@RequestBody UpdateSubscriptionDTO subscripiton) throws Exception {
		HttpResponseDTO response = subscriptionService.updateSubscription(subscripiton);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> editSubscription(@RequestBody EditSubscriptionDTO subscripiton) throws Exception {
		HttpResponseDTO response = subscriptionService.editSubscription(subscripiton);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> listSubscription() throws Exception {
		return super.response(subscriptionService.listSubscription(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getStudent(@PathVariable("id") String id) throws Exception {
		HttpResponseDTO response = subscriptionService.getSubscription(id);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(path = "/query/complex", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getSubscriptionByParams(@RequestParam(required = false) String id,
			@RequestParam(required = false) Long code, @RequestParam(required = false) String idStudent,
			@RequestParam(required = false) String edition) throws Exception {
		HttpResponseDTO response = subscriptionService.getSubscriptionByParams(id, code, idStudent, edition);
		return super.response(response, response.getStatus());
	}
	
	@RequestMapping(path = "/query/simple", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HttpResponseDTO> getSubscriptionByParam(String value) throws Exception {
		HttpResponseDTO response = subscriptionService.getSubscriptionByParam(value);
		return super.response(response, response.getStatus());
	}
}

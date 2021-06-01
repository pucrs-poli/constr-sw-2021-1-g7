package csw.service.logic;

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

import csw.dto.AddSubscriptionDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.SubscriptionDTO;
import csw.dto.UpdateSubscriptionDTO;
import csw.models.Subscription;
import csw.repository.SubscriptionRepository;

@SpringBootTest
public class SubscriptionServiceTests {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	static private ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();	
	
	private int totalSubscriptions;
	
	@BeforeEach
	void createrUserMocks() {
		Subscription newSubscription = subscriptionRepository
				.save(new Subscription((long) 555555, "5", "Edição teste 5", new ArrayList<>()));
		Subscription newSubscription1 = subscriptionRepository
				.save(new Subscription((long) 666666, "6", "Edição teste 6", new ArrayList<>()));
		Subscription newSubscription2 = subscriptionRepository
				.save(new Subscription((long) 777777, "7", "Edição teste 7", new ArrayList<>()));
		subscriptions.add(newSubscription);
		subscriptions.add(newSubscription1);
		subscriptions.add(newSubscription2);
		totalSubscriptions = subscriptionRepository.findAll().size();
	}
	
	@AfterEach
	void clearUserMocks() {
		subscriptionRepository.deleteAll(subscriptions);
		subscriptions = new ArrayList<>();
	}
	
	@Test
	void mustRegisterASubscription() {
		HttpResponseDTO dto = subscriptionService.registerSubscription(new AddSubscriptionDTO((long) 88888, "8", "Edição teste 8", new ArrayList<>()));
		SubscriptionDTO subscriptionDTO = (SubscriptionDTO) dto.getContent().get("subscription");
		assertNotEquals(subscriptionDTO.getIdSubscription(), null);			
		subscriptions.add(subscriptionService.map(subscriptionDTO, Subscription.class));
	}
	
	@Test
	void mustDeleteSubscription() {
		HttpResponseDTO dto = subscriptionService.deleteSubscription(subscriptions.get(0).getIdSubscription());
		assertEquals(dto.getSuccess(), Boolean.TRUE);
	}
	
	@Test
	void mustFindAValidSubscriptionById() {
		HttpResponseDTO dto = subscriptionService.getSubscription(subscriptions.get(0).getIdSubscription());
		SubscriptionDTO subscriptionDTO = (SubscriptionDTO) dto.getContent().get("subscription");
		assertEquals(subscriptionDTO.getIdSubscription(), subscriptions.get(0).getIdSubscription());	
	}
	
	@Test
	void mustGetAllSubscriptions() {
		HttpResponseDTO dto = subscriptionService.listSubscription();
		List<Subscription> subscriptions = (List<Subscription>) dto.getContent("subscriptions");
		assertEquals(subscriptions.size() , totalSubscriptions);
	}
	
	@Test
	void mustEditAValidSubscription() {
		HttpResponseDTO dto = subscriptionService.updateSubscription(new UpdateSubscriptionDTO(subscriptions.get(0).getIdSubscription(),(long) 7843734, "100", "Edição editada teste", new ArrayList<>()));
		SubscriptionDTO subscriptionDTO = (SubscriptionDTO) dto.getContent().get("subscription");
		assertEquals(subscriptionDTO.getIdSubscription(), subscriptions.get(0).getIdSubscription());	
	}
	
	@Test
	void mustFindASubscriptionByAValidParam() {
		HttpResponseDTO dto = subscriptionService.getSubscriptionByParam(subscriptions.get(1).getIdSubscription());
		List<SubscriptionDTO> contentDtos = (List<SubscriptionDTO>) dto.getContent().get("subscriptions");		
		assertEquals(contentDtos.get(0).getCode(), subscriptions.get(1).getCode());
	}
	
	@Test
	void mustFindSubscriptionByValidParams() {
		HttpResponseDTO dto = subscriptionService.getSubscriptionByParams(null, subscriptions.get(0).getCode(), null,subscriptions.get(0).getEdition());
		List<SubscriptionDTO> contentDtos = (List<SubscriptionDTO>) dto.getContent().get("subscriptions");		
		assertEquals(contentDtos.get(0).getCode(), subscriptions.get(0).getCode());
		assertEquals(contentDtos.get(0).getEdition(), subscriptions.get(0).getEdition());
	}
}

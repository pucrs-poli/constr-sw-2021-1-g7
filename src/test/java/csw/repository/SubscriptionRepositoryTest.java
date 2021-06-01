package csw.repository;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import csw.models.Subscription;

@SpringBootTest
public class SubscriptionRepositoryTest {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	private int totalSubscriptions;

	static private ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
	
	@BeforeEach
	void createrUserMocks() {
		Subscription newSubscription = subscriptionRepository
				.save(new Subscription((long) 111111, "1", "Edição teste 1", new ArrayList<>()));
		Subscription newSubscription1 = subscriptionRepository
				.save(new Subscription((long) 222222, "2", "Edição teste 2", new ArrayList<>()));
		Subscription newSubscription2 = subscriptionRepository
				.save(new Subscription((long) 333333, "3", "Edição teste 3", new ArrayList<>()));
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
	void mustInsertValidSubscription() {
		Subscription newSubscription = subscriptionRepository
				.save(new Subscription((long) 444444, "4", "Edição teste 4", new ArrayList<>()));
		assertNotEquals(newSubscription, null);
		subscriptions.add(newSubscription);
	}
	
	@Test
	void mustDeleteValidSubscription() {
		Subscription subscription = subscriptionRepository.findById(subscriptions.get(0).getIdSubscription()).get();
		assertNotEquals(subscription, null);
		this.subscriptionRepository.delete(subscriptions.get(0));
		assertEquals(subscriptionRepository.existsById(subscription.getIdSubscription()), false);
	}
	
	@Test
	void mustFindAValidSubscriptionById() {
		Subscription subscription = subscriptionRepository.findById(subscriptions.get(0).getIdSubscription()).get();
		assertNotEquals(subscription, null);
	}
	
	@Test
	void mustGetAllSubscriptions() {
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		assertEquals(subscriptions.size(), totalSubscriptions);
	}
	
	@Test
	void mustEditAValidSubscription() {
		Subscription subscription = subscriptionRepository.findById(subscriptions.get(0).getIdSubscription()).get();
		subscription.setCode((long) 32423423);
		subscription.setIdStudent("10");
		subscription.setEdition("Edição editada 1");
		subscription.setTests(new ArrayList<>());
		Subscription subscriptionEdited = subscriptionRepository.save(subscription);
		assertNotEquals(subscriptionEdited, null);
		assertEquals(subscriptionEdited.getCode(), 32423423);
		assertEquals(subscriptionEdited.getIdStudent(), "10");
		assertEquals(subscriptionEdited.getEdition(), "Edição editada 1");
		assertEquals(subscriptionEdited.getTests(), new ArrayList<>());
	}
	
	@Test
	void mustFindAValidSubscriptionByAValidParam() {
		Subscription subscription = subscriptionRepository.findById(subscriptions.get(1).getIdSubscription()).get();
		List<Subscription> subscriptions = subscriptionRepository.findByParam(subscription.getEdition());		
		assertEquals(subscriptions.get(0).getEdition(), subscription.getEdition());
	}
	
	@Test
	void mustFindAValidSubscriptionByValidParams() {
		Subscription subscription = subscriptionRepository.findById(subscriptions.get(1).getIdSubscription()).get();
		List<Subscription> subscriptions = subscriptionRepository.findByIdSubscriptionOrCodeOrIdStudentOrEdition(subscription.getIdSubscription(), null, null, subscription.getEdition());
		assertEquals(subscriptions.get(0).getIdSubscription(), subscription.getIdSubscription());
		assertEquals(subscriptions.get(0).getEdition(), subscription.getEdition());
	}


}

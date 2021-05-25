package csw.service.logic;

import org.springframework.beans.factory.annotation.Autowired;

import csw.repository.SubscriptionRepository;

public class SubscriptionService extends AbstractService {

	@Autowired
	SubscriptionRepository subscriptionRepository;
}

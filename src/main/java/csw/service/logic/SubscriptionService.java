package csw.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csw.repository.SubscriptionRepository;

@Service
public class SubscriptionService extends AbstractService {

	@Autowired
	SubscriptionRepository subscriptionRepository;
}

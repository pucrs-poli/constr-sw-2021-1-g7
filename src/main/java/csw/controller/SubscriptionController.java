package csw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csw.service.logic.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController extends AbstractController {

	@Autowired
	private SubscriptionService subscriptionService;
}

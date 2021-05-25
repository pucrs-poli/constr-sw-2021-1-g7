package csw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import csw.models.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
}
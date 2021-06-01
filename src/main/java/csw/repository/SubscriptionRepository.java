package csw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import csw.models.Student;
import csw.models.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
	
	List<Subscription> findByIdSubscriptionOrCodeOrIdStudentOrEdition(String id, Long code, String idStudent, String edition);
	
	@Query("{ $or: [{'idSubscription' : ?0}, {'code' : ?0}, {'idStudent' : ?0}, {'edition' : ?0}] }")
	List<Subscription> findByParam(String param);
	
	List<Subscription> findByIdStudent(String id);
}
package csw.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import csw.domain.Messages;
import csw.dto.AddSubscriptionDTO;
import csw.dto.EditSubscriptionDTO;
import csw.dto.HttpResponseDTO;
import csw.dto.ResultDTO;
import csw.dto.SubscriptionDTO;
import csw.dto.UpdateSubscriptionDTO;
import csw.models.Subscription;
import csw.repository.SubscriptionRepository;
import csw.service.consumer.EvaluationServiceConsumer;

@Service
public class SubscriptionService extends AbstractService {

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	EvaluationServiceConsumer evaluationServiceConsumer;

	public HttpResponseDTO registerSubscription(AddSubscriptionDTO subscription) {
		this.LogServiceConsumed(this.getClassName(), "registerSubscription");
		Subscription subscriptionSaved = this.subscriptionRepository.save(super.map(subscription, Subscription.class));
		if (subscriptionSaved != null) {
			return HttpResponseDTO.success("subscription", super.map(subscriptionSaved, SubscriptionDTO.class), HttpStatus.CREATED);
		} else {
			return HttpResponseDTO.fail(Messages.A007, "Erro ao salvar nova inscrição.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public HttpResponseDTO deleteSubscription(String id) {
		this.LogServiceConsumed(this.getClassName(), "deleteSubscription");
		try {
			this.subscriptionRepository.deleteById(id);
			return HttpResponseDTO.success("Inscrição Removida com sucesso.", Messages.A008);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResponseDTO.fail(Messages.A009, "Erro ao remover Inscrição.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public HttpResponseDTO updateSubscription(UpdateSubscriptionDTO subscripiton) {
		this.LogServiceConsumed(this.getClassName(), "updateSubscription");

		if (this.subscriptionRepository.findById(subscripiton.getIdSubscription()).isPresent()) {

			Subscription subscripitonSaved = this.subscriptionRepository.save(super.map(subscripiton, Subscription.class));

			if (subscripitonSaved != null) {
				return HttpResponseDTO.success("subscription", super.map(subscripitonSaved, SubscriptionDTO.class));
			} else {
				return HttpResponseDTO.fail(Messages.A010, "Erro ao atualizar inscrição.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return HttpResponseDTO.fail(Messages.A011, "Inscrição não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO editSubscription(EditSubscriptionDTO subscripiton) {
		this.LogServiceConsumed(this.getClassName(), "editSubscription");

		if (this.subscriptionRepository.findById(subscripiton.getIdSubscription()).isPresent()) {

			Subscription subscriptionSaved = this.subscriptionRepository.save(super.map(subscripiton, Subscription.class));

			if (subscriptionSaved != null) {
				return HttpResponseDTO.success("subscription", super.map(subscriptionSaved, SubscriptionDTO.class));
			} else {
				return HttpResponseDTO.fail(Messages.A012, "Erro ao editar inscrição.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return HttpResponseDTO.fail(Messages.A011, "Inscrição não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO listSubscription() {
		this.LogServiceConsumed(this.getClassName(), "listSubscription");
		return HttpResponseDTO.success("subscriptions", super.mapAll(this.subscriptionRepository.findAll(), SubscriptionDTO.class));
	}

	public HttpResponseDTO getSubscription(String id) {
		this.LogServiceConsumed(this.getClassName(), "getStudent");
		Subscription sbc = this.subscriptionRepository.findById(id).orElse(null);
		if (sbc != null) {
			return HttpResponseDTO.success("subscription", super.map(sbc, SubscriptionDTO.class));
		} else {
			return HttpResponseDTO.fail(Messages.A011, "Inscrição não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO getSubscriptionByParams(String id, Long code, String idStudent, String edition) {
		this.LogServiceConsumed(this.getClassName(), "getSubscriptionByParams");
		List<Subscription> subscriptions = this.subscriptionRepository.findByIdSubscriptionOrCodeOrIdStudentOrEdition(id, code, idStudent,
				edition);
		if (subscriptions != null) {
			return HttpResponseDTO.success("subscriptions", super.mapAll(subscriptions, Subscription.class));
		} else {
			return HttpResponseDTO.fail(Messages.A011, "Inscrição não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

	public HttpResponseDTO getSubscriptionByParam(String value) {
		this.LogServiceConsumed(this.getClassName(), "getSubscriptionByParam");
		List<Subscription> subscriptions = this.subscriptionRepository.findByParam(value);
		if (subscriptions != null) {
			return HttpResponseDTO.success("subscriptions", super.mapAll(subscriptions, Subscription.class));
		} else {
			return HttpResponseDTO.fail(Messages.A005, "Inscrição não encontrada.", HttpStatus.NOT_FOUND);
		}
	}
	
	public HttpResponseDTO getResultsBySubscriptionId(String token, String id) {
		this.LogServiceConsumed(this.getClassName(), "getResultsBySubscriptionId");
		List<ResultDTO> list = evaluationServiceConsumer.requestResultsBySubscriptionId(token, id);
		return HttpResponseDTO.success("results", list);
	}

	public List<SubscriptionDTO> getSubscriptionByStudentId(String id) {
		List<Subscription> subscriptions = this.subscriptionRepository.findByIdStudent(id);
		return super.mapAll(subscriptions, SubscriptionDTO.class);
	}

}

package csw.service.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import csw.dto.ResultDTO;
import csw.utility.Address;

@Service
public class EvaluationServiceConsumer extends AbstractServiceConsumer {

	public List<ResultDTO> requestResultsBySubscriptionId(String token, String id) {
		try {
			List<ResultDTO> list = super.get(new ParameterizedTypeReference<List<ResultDTO>>() {
			}, "/tests/" + id +"/results", new HashMap<>(), Address.EVALUATIONS, token);
			return list;
		} catch (Exception e) {
			if ("N".equals(evaluation_base_url)) { //MOCK ATIVADO...
				List<ResultDTO> list = new ArrayList<ResultDTO>();
				list.add(new ResultDTO("b32432f4b202a58b5fe25", 7.5, "60b05a0fef6b202a58b5fe25"));
				list.add(new ResultDTO("b435fb435fb435f435bf4", 8.4, "60af0ffd71f7d15e2f6d22e5"));
				list.add(new ResultDTO("b345fj34b5h34b5334543", 9.2, "60af0ffd71f7d15e2f6d22e5"));				
				return list;
			} else {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}
	}
}

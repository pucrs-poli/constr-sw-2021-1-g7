package csw.service.logic;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csw.utility.ObjectMapperUtils;
/**
 * Abstract class containing the Daos and 
 * other things useful to all services classes. 
 * @author Eduardo Dornelles
 *
 */
@Service
public class AbstractService {
	
	@Autowired
	protected Logger logger;
	
	/**
	 * method to log when a service is consumed and
	 * also the method inside that will be used.
	 * @param serviceConsumed
	 * @param methodName
	 */
	protected void LogServiceConsumed(String serviceConsumed, String methodName){
		this.logger.info("CONSUMIDO SERVIÇO - "+ serviceConsumed); 
		this.logger.info("MÉTODO UTILIZADO - "+ methodName);		
	}
	
	/**
	 * return the complete name of the class.
	 * @return
	 */
	protected String getClassName() {
		return this.getClass().getName();
	}
}

package csw.service.logic;

import java.util.Collection;
import java.util.List;

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
	
	protected <D, T> D map(final T entity, Class<D> outClass) {
		return ObjectMapperUtils.getInstancia().map(entity, outClass);
	}
	
	protected <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return ObjectMapperUtils.getInstancia().mapAll(entityList, outCLass);
	}
}

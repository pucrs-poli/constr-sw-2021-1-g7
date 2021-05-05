package csw.utility;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtils {

	private static ObjectMapperUtils instance = null;
    private static ModelMapper modelMapper = new ModelMapper();
    
    /**
     * SINGLETON
     */
	public static synchronized ObjectMapperUtils getInstancia() {
		if (instance == null) {
			instance = new ObjectMapperUtils();
		}
		return instance;
	}
	

    private ObjectMapperUtils() {
    }

    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
    
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
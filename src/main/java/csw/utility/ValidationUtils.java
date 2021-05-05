package csw.utility;

import csw.domain.Messages;
import csw.domain.Parameters;
import csw.model.User;

/**
 * class containing validations that are used through the application
 * 
 * @author Eduardo Dornelles
 *
 */
public class ValidationUtils {

	/**
	 * method to validate all fields and the object received when an user sign-up.
	 * 
	 * @param user
	 * @return
	 */
	public static String validateUserFields(User user) {
		if (user == null)
			return Messages.A003;
		if (user.getSenha() == null || user.getNome() == null)
			return Messages.A004;
		if (user.getSenha() == "" || user.getNome() == "")
			return Messages.A004;
		if (user.getSenha().length() < Parameters.PASSWORD_MIN_SIZE)
			return Messages.A006;
		return null;
	}	

}

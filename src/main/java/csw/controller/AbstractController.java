package csw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Abstract controller implementing default
 * methods for some actions.
 */
public class AbstractController {

	/**
	 * Form a ResponseEnity based on
	 * a body and a http status
	 *
	 * @param body
	 *            response body
	 * @param status
	 *            response status code
	 * @return {@link ResponseEntity} to be returned
	 *         to the client.
	 */
	public <T> ResponseEntity<T> response(final T body, final HttpStatus status) {
		return new ResponseEntity<>(body, status);
	}
}

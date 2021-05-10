package csw.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import csw.utility.GeneralUtils;

/**
 * Classe para padronizar as respostas das chamadas em um response.
 *
 */
public class HttpResponseDTO {

	/**
	 * Success indicator for the request.
	 */
	private Boolean success;

	/**
	 * A series of messages to be returned to the client.
	 */
	private Collection<MessageDTO> messages;

	/**
	 * A series o objects to be returned to the client.
	 */
	private Map<String, Object> content;
	
	/**
	 * Represent the status of the call.
	 */
	private HttpStatus status;

	/**
	 * Verifies the success of the operation.
	 *
	 * @return Operation success indication.
	 */
	public Boolean isSuccess() {
		return this.success;
	}

	/**
	 * Defines the success of the operation
	 *
	 * @param success
	 *            the success indicator.
	 */
	public void setSuccess(final Boolean success) {
		this.success = success;
	}

	/**
	 * Get the messages list.
	 *
	 * @return {@link MessageDTO}
	 */
	public Collection<MessageDTO> getMessages() {
		if (Objects.isNull(this.messages)) {
			this.messages = new ArrayList<>();
		}
		return this.messages;
	}

	/**
	 * Get a {@link MessageDTO} based on an index.
	 *
	 * @param index
	 *            {@link Integer} containing the index
	 * @return {@link MessageDTO}
	 */
	public MessageDTO getMessage(final Integer index) {

		if ((!Objects.isNull(getMessages())) && (getMessages().size() >= (index - 1))) {
			return getMessages().toArray(new MessageDTO[0])[index];
		} else {
			return null;
		}
	}

	/**
	 * Get a {@link MessageDTO} based on an code.
	 *
	 * @param code
	 *            {@link String} representing the message code.
	 * @return {@link MessageDTO}.
	 */
	public MessageDTO getMessage(final String code) {

		if (!Objects.isNull(getMessages())) {
			return getMessages().stream().filter(m -> m.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
		} else {
			return null;
		}
	}

	/**
	 * Add a message to the list.
	 *
	 * @param message
	 *            {@link MessageDTO} containing message and code.
	 */
	public void addMessage(final MessageDTO message) {
		if (!GeneralUtils.isEmpty(message)) {
			getMessages().add(message);
		}

	}

	/**
	 * Add a message to the list without code.
	 *
	 * @param message
	 */
	public void addMessage(final String message) {
		if (!GeneralUtils.isEmpty(message)) {
			getMessages().add(new MessageDTO(message, null));
		}

	}

	/**
	 * Add a message to the list with code.
	 *
	 * @param message
	 * @param code
	 */
	public void addMessage(final String message, final String code) {
		if (!GeneralUtils.isEmpty(message)) {
			getMessages().add(new MessageDTO(message, code));
		}

	}

	/**
	 * Add all messages from a collection to the list.
	 *
	 * @param messages
	 *            {@link Collection<MessageDTO>} Collection of error messages.
	 */
	public void addMessages(final Collection<MessageDTO> messages) {
		if (!GeneralUtils.isEmpty(messages)) {
			getMessages().addAll(messages);
		}
	}
	
	/**
	 * Get the map representing the content.
	 *
	 * @return {@link Map} with the content.
	 */
	public Map<String, Object> getContent() {

		if (Objects.isNull(this.content)) {
			this.content = new HashMap<>();
		}

		return this.content;
	}

	/**
	 * Get the value of the specified key.
	 *
	 * @param key
	 *            {@link String} key to be searched in the map.
	 * @return {@link Object} for the key.
	 */
	public Object getContent(final String key) {
		if (!Objects.isNull(this.getContent())) {
			return this.getContent().get(key);
		} else {
			return null;
		}

	}

	/**
	 * Adds the object as the specified key.
	 *
	 * @param key
	 *            {@link String} containing a key for the map.
	 * @param value
	 *            {@link Object} being the value for the key.
	 */
	public void addContent(final String key, final Object value) {
		if (!GeneralUtils.isEmpty(value)) {
			this.getContent().put(key, value);
		}
	}

	/**
	 * Adds the content using the {@link Object} class name as key.
	 *
	 * @param value
	 *            {@link Object} to be part of content.
	 */
	public void addContent(final Object value) {
		if (!GeneralUtils.isEmpty(value)) {
			final String key = value.getClass().getSimpleName();
			this.getContent().put(key, value);
		}
	}
	
	/**
	 * @param status
	 *            {@link #status}
	 */
	public void setStatus(final HttpStatus status) {
		this.status = status;
	}
	
	/**
	 * @return {@link #status}
	 */
	public HttpStatus getStatus() {
		if (GeneralUtils.isEmpty(this.status)) {
			return HttpStatus.OK;
		}
		return this.status;
	}

	/**
	 * Fail response with message by MessageDTO.
	 *
	 * @param message
	 *            message
	 * @return fail response.
	 */
	public static HttpResponseDTO fail(final MessageDTO message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.addMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}
	
	public static HttpResponseDTO fail(final HttpStatus httpStatus) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.FALSE);
		response.setStatus(httpStatus);
		return response;
	}

	/**
	 * Fail response with message by code and message
	 *
	 * @param code
	 *            message code
	 * @param message
	 *            message text
	 * @return fail response
	 */
	public static HttpResponseDTO fail(final String code, final String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.addMessage(message, code);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}
	
	public static HttpResponseDTO fail(final String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.addMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}
	
	/**
	 * Fail response with message by code and message
	 *
	 * @param code
	 *            message code
	 * @param message
	 *            message text
	 * @param status
	 * @return fail response
	 */
	public static HttpResponseDTO fail(final String code, final String message, final HttpStatus status) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.addMessage(message, code);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(status);
		return response;
	}
	
	public static HttpResponseDTO fail(final String message, final HttpStatus status) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.addMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(status);
		return response;
	}

	/**
	 * Success response without content.
	 *
	 * @return a success response.
	 */
	public static HttpResponseDTO success() {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	/**
	 * Success response with single content.
	 *
	 * @param key
	 *            content key
	 * @param content
	 *            content value
	 * @return Success response with single content.
	 */
	public static HttpResponseDTO success(final String key, final Object content) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.addContent(key, content);
		response.setStatus(HttpStatus.OK);
		return response;
	}
	
	public static HttpResponseDTO success(final Object content) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.addContent(content);
		response.setStatus(HttpStatus.OK);
		return response;
	}
	
	public static HttpResponseDTO success(final String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.addMessage(message);
		response.setStatus(HttpStatus.OK);
		return response;
	}
	
	/**
	 * Success response with single content.
	 *
	 * @param key
	 *            content key
	 * @param content
	 *            content value
	 * @param status
	 * @return Success response with single content.
	 */
	public static HttpResponseDTO success(final String key, final Object content, final HttpStatus status) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.addContent(key, content);
		response.setStatus(status);
		return response;
	}
	
	public static HttpResponseDTO success(final HttpStatus status) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.setStatus(status);
		return response;
	}
}

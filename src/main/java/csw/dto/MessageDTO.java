package csw.dto;

public class MessageDTO {
	
	/**
	 * Message content
	 */
	private String message;
	
	/**
	 * Message code
	 */
	private String code;

	public MessageDTO() {}

	public MessageDTO(final String message, final String code) {
		this.message = message;
		this.code = code;
	}
	
	/**
	 * @return {@link #code}
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * @param code
	 *            {@link #code}
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	
	/**
	 * @return {@link #message}
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * @param message
	 *            {@link #message}
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public boolean equals(final Object obj) {
		final MessageDTO message = (MessageDTO) obj;

		return (message.message.equals(this.message) && message.code.equals(this.code));
	}
}

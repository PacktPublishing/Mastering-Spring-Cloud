package pl.piomin.services.gateway.fallback.response;

public class AccountFallbackResponse {

	private String errorCode;
	private String errorMessage;

	public AccountFallbackResponse() {

	}

	public AccountFallbackResponse(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

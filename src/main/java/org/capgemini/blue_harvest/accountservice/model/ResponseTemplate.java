package org.capgemini.blue_harvest.accountservice.model;
public class ResponseTemplate {
	private String message;
	private String code;
	private Object body;

    public ResponseTemplate() {
    }

    
	public ResponseTemplate(String message, Object body) {
		super();
		this.message = message;
		this.body = body;
	}


	public ResponseTemplate(String message, String code, Object body) {
		super();
		this.message = message;
		this.code = code;
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

    
}


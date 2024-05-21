package org.capgemini.blue_harvest.accountservice.model;

public class TransactionResponse {
    private String message;
    private String code;
    private Transaction body;

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

    public Transaction getBody() {
        return body;
    }

    public void setBody(Transaction body) {
        this.body = body;
    }
}

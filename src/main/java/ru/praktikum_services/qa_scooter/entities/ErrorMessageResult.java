package ru.praktikum_services.qa_scooter.entities;


public class ErrorMessageResult {
    Integer code;
    String message;

    public ErrorMessageResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessageResult() {
    }

    @Override
    public String toString() {
        return String.format("ErrorMessageResult {" +
                "code=%s" +
                ", message='%s'" +
                '}', code, message);
//        Gson gson = new Gson();
//        return gson.toJson(new ErrorMessageResult(code, message));
    }
}

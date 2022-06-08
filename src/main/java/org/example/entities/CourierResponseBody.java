package org.example.entities;

import com.google.gson.Gson;

public class CourierResponseBody {
    private Boolean ok;
    private Integer code;
    private String message;

    public CourierResponseBody() {
    }

    public CourierResponseBody(Boolean ok, Integer code, String message) {
        this.ok = ok;
        this.code = code;
        this.message = message;
    }

    public Boolean isOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
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

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(new CourierResponseBody(ok, code, message));
    }
}

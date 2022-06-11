package ru.praktikum_services.qa_scooter.entities;

import com.google.gson.Gson;

public class SimplePositiveResponse {
    private Boolean ok;
    public SimplePositiveResponse() {
    }

    public SimplePositiveResponse(Boolean ok) {
        this.ok = ok;
    }

    public Boolean isOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(new SimplePositiveResponse(ok));
    }
}

package ru.praktikum_services.qa_scooter.entities;

import com.google.gson.Gson;

public class CourierResponse {
    private Boolean ok;
    public CourierResponse() {
    }

    public CourierResponse(Boolean ok) {
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
        return gson.toJson(new CourierResponse(ok));
    }
}

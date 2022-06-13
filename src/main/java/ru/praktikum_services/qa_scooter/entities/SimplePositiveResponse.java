package ru.praktikum_services.qa_scooter.entities;

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
        return String.format("SimplePositiveResponse {" +
                "ok=%s" +
                '}', ok);
//        Gson gson = new Gson();
//        return gson.toJson(new SimplePositiveResponse(ok));
    }
}

package ru.praktikum_services.qa_scooter.entities;

public class LoginCourierResponse {
    private Integer id;

    public LoginCourierResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LoginCourierResponse(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("LoginCourier {" +
                "id=%s" +
                '}', id);
    }
}

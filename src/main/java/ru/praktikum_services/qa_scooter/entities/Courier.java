package ru.praktikum_services.qa_scooter.entities;

import ru.praktikum_services.qa_scooter.helpers.RandomSequences;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier() {
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getRandomCourier() {
        String randomLogin = RandomSequences.createRandomUuid();
        String randomPassword = RandomSequences.createRandomPassword(12);
        String randomFirstName = RandomSequences.getRandomName();
        return new Courier(randomLogin, randomPassword, randomFirstName);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return String.format("Courier {" +
                "login='%s'" +
                ", password='%s'" +
                ", firstName='%s" +
                '}', login, password, firstName);
//        Gson gson = new Gson();
//        return gson.toJson(new Courier(login, firstName, password));
//
    }
}

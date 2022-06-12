package ru.praktikum_services.qa_scooter.entities;

import ru.praktikum_services.qa_scooter.helpers.enums.DateEra;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;

import java.util.List;

public class Order {
    /**
     * <a href="http://qa-scooter.praktikum-services.ru/docs/#api-Orders-CreateOrder"> Документация </a>
     */
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime,
                 String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order() {
    }

    public static Order getRandomRequiredArgsOrder() {
        String firstName = RandomSequences.getRandomName();
        String lastName = RandomSequences.getRandomName();
        String address = RandomSequences.getRandomAddress();
        String metroStation = RandomSequences.getRandomMetroStation();
        String phone = RandomSequences.getRandomPhoneNumber();
        int rentTime = RandomSequences.getRandomRentTime();
        String deliveryDate = RandomSequences.getDate(DateEra.DAY_IN_PRESENT);
        String comment = "Some comment for courier";
        List<String> color = null;
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}

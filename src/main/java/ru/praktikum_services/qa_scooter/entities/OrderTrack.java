package ru.praktikum_services.qa_scooter.entities;

public class OrderTrack {
    private Integer track;

    public OrderTrack() {
    }

    public OrderTrack(int track) {
        this.track = track;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return String.format("OrderTrack {" +
                "track=%s" +
                '}', track);
    }
}

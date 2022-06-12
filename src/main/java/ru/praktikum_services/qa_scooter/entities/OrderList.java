package ru.praktikum_services.qa_scooter.entities;

import java.util.List;

public class OrderList {
    private List<OrderResponse> orders;
    private PageInfo pageInfo;
    private List<MetroStation> availableStations;

    public OrderList() {
    }

    public List<OrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponse> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<MetroStation> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<MetroStation> availableStations) {
        this.availableStations = availableStations;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "orders=" + orders +
                ", pageInfo=" + pageInfo +
                ", availableStations=" + availableStations +
                '}';
    }
}

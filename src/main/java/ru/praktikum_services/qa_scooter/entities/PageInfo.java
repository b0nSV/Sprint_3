package ru.praktikum_services.qa_scooter.entities;

public class PageInfo {
    private Integer page;
    private Integer total;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public PageInfo() {
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "page=" + page +
                ", total=" + total +
                ", limit=" + limit +
                '}';
    }
}

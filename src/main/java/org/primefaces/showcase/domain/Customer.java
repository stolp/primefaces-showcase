package org.primefaces.showcase.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    private int id;
    private String name;
    private Country country;
    private LocalDate date;
    private CustomerStatus status;
    private int activity;
    private Representative representative;

    public Customer() {}

    public Customer(int id, String name, Country country, LocalDate date, CustomerStatus status, int activity, Representative representative) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.date = date;
        this.status = status;
        this.activity = activity;
        this.representative = representative;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }
}

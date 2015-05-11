package com.application.model;

/**
 * Created by Alexander on 11.05.2015.
 */
public class AppointRepair {

    private Integer id;
    private String model;
    private String number;
    private String phone;
    private String type;
    private String state;
    private String typeMF;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTypeMF() {
        return typeMF;
    }

    public void setTypeMF(String typeMF) {
        this.typeMF = typeMF;
    }
}

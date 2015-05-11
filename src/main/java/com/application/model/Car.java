package com.application.model;


/**
 * Created by Alexander on 04.05.2015.
 */
public class Car {

    private Integer id;
    private String  carName;
    private String carNumber;
    private String carState;
    private String carType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car { " +
                "id= " + id +
                ", carName= '" + carName + '\'' +
                ", carNumber= '" + carNumber + '\'' +
                ", carState= '" + carState + '\'' +
                ", carType= '" + carType + '\'' +
                " } \n";
    }
}

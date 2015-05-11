package com.application.model;



import javax.imageio.stream.FileImageInputStream;
import java.io.Serializable;

/**
 * Created by Alexander on 04.05.2015.
 */
public class CarDetalis {

    private Integer id;
    private String  carName;
    private String carNumber;
    private String carType;
    private String carState;
    private String carTonnage;
    private String carPhoneNumber;
    private String carGradYear;

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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getCarTonnage() {
        return carTonnage;
    }

    public void setCarTonnage(String carTonnage) {
        this.carTonnage = carTonnage;
    }

    public String getCarPhoneNumber() {
        return carPhoneNumber;
    }

    public void setCarPhoneNumber(String carPhoneNumber) {
        this.carPhoneNumber = carPhoneNumber;
    }

    public String getCarGradYear() {
        return carGradYear;
    }

    public void setCarGradYear(String carGradYear) {
        this.carGradYear = carGradYear;
    }

    @Override
    public String toString() {
        return "CarDetalis { " +
                "id= " + id +
                ", carName= '" + carName + '\'' +
                ", carNumber= '" + carNumber + '\'' +
                ", carType= '" + carType + '\'' +
                ", carState= '" + carState + '\'' +
                ", carTonnage= '" + carTonnage + '\'' +
                ", carPhoneNumber= '" + carPhoneNumber + '\'' +
                ", carGradYear= '" + carGradYear + '\'' +
                " }";
    }
}

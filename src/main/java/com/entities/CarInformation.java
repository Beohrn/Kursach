package com.entities;

/**
 * Created by Alexander on 20.04.2015.
 */


public class CarInformation {

    private int idCar;
    private String modelCar;
    private String state;
    private String number;

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CarInformation{" +
                "idCar=" + idCar +
                ", modelCar='" + modelCar + '\'' +
                ", state='" + state + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

}

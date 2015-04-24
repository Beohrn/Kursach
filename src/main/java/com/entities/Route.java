package com.entities;

/**
 * Created by Alexander on 18.04.2015.
 */
public class Route {

    private int idRoute;
    private String car;

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Route{" +
                "idRoute=" + idRoute +
                ", car='" + car + '\'' +
                '}';
    }
}

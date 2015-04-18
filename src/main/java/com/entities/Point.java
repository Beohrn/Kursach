package com.entities;

import java.util.Date;

/**
 * Created by Alexander on 18.04.2015.
 */
public class Point {

    private int object;
    private int route;
    private int operation;
    private Date plan;
    private Date fact;

    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Date getPlan() {
        return plan;
    }

    public void setPlan(Date plan) {
        this.plan = plan;
    }

    public Date getFact() {
        return fact;
    }

    public void setFact(Date fact) {
        this.fact = fact;
    }
}

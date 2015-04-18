package com.entities;

/**
 * Created by Alexander on 18.04.2015.
 */
public class R_State {

    private int idState;
    private String value;

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "R_State{" +
                "idState=" + idState +
                ", value='" + value + '\'' +
                '}';
    }
}

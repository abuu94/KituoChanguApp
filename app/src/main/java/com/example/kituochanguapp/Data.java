package com.example.kituochanguapp;

public class Data {
    private String mysheha, mystation, myXcordina,myYcordina,time;

    public Data(String mysheha, String mystation, String myXcordina, String myYcordina,String time) {
        this.mysheha = mysheha;
        this.mystation = mystation;
        this.myXcordina = myXcordina;
        this.myYcordina = myYcordina;
        this.time=time;
    }

    public Data(){

    }

    public String getMysheha() {
        return mysheha;
    }

    public void setMysheha(String mysheha) {
        this.mysheha = mysheha;
    }

    public String getMystation() {
        return mystation;
    }

    public void setMystation(String mystation) {
        this.mystation = mystation;
    }

    public String getMyXcordina() {
        return myXcordina;
    }

    public void setMyXcordina(String myXcordina) {
        this.myXcordina = myXcordina;
    }

    public String getMyYcordina() {
        return myYcordina;
    }

    public void setMyYcordina(String myYcordina) {
        this.myYcordina = myYcordina;
    }

    public String getTime(){ return time; }

    public void setTime(String time){ this.time=time;}
}

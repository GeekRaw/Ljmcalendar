package com.jnu.ljmcalendar.dataprocessor;

import java.io.Serializable;

public class Record implements Serializable {

    private String type,name,money,reason,date;

    public Record(String type, String name, String money, String reason, String date){
        this.type = type;
        this.name = name;
        this.money = money;
        this.reason = reason;
        this.date = date;
    }

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getMoney() {
        return money;
    }
    public String getReason() {
        return reason;
    }
    public String getDate(){return date;}

    public void setType(String type) { this.type = type; }
    public void setName(String name) {
        this.name = name;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setDate(String date){this.date = date;}

}

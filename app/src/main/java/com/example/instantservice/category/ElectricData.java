package com.example.instantservice.category;

public class ElectricData {
    String itemname,itemcost,phoneNO;

    public ElectricData() {
    }

    public ElectricData(String itemname, String itemcost, String phoneNO) {
        this.itemname = itemname;
        this.itemcost = itemcost;
        this.phoneNO = phoneNO;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemcost() {
        return itemcost;
    }

    public void setItemcost(String itemcost) {
        this.itemcost = itemcost;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }
}

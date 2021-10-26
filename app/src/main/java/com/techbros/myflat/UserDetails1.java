package com.techbros.myflat;

class UserDetails1 {

    String index,block,flatNumber,name,phone,occupied,tenantName;


    public UserDetails1(String index, String block, String flatNumber, String name, String phone, String occupied, String tenantName) {
        this.index = index;
        this.block = block;
        this.flatNumber = flatNumber;
        this.name = name;
        this.phone = phone;
        this.occupied = occupied;
        this.tenantName = tenantName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

}

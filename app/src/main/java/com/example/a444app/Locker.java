package com.example.a444app;

public class Locker {
    private int id;
    private int area;//todo change to int
    private String sId;
    private boolean availability; //todo chab=nge type
    private String size;

    public Locker() {
    }

    public Locker(int id, int area, String sId, boolean availability, String size) {
        this.id = id;
        this.area = area;
        this.sId = sId;
        this.availability = availability;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

package com.example.bai_3;

public class Event {
    private int id;
    private String name;
    private String place;
    private String date;
    private String time;
    private Boolean done;

    public Event() {
    }

    public Event(int id, String name, String place, String date, String time, Boolean done) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public Event(String name, String place, String date, String time) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        setDone(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", done=" + done +
                '}';
    }
}

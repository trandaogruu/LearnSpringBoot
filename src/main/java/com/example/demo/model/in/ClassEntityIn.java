package com.example.demo.model.in;


import java.util.ArrayList;
import java.util.List;

public class ClassEntityIn {


    private int id;

    private String name;

    private boolean status;

    private List<Integer> studentEntities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(List<Integer> studentEntities) {
        this.studentEntities = studentEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

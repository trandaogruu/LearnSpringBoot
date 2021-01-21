package com.example.demo.model.entities;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class StudentEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private Date birthday;

    @Column
    private String address;

    @Column
    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name = "class")
    private ClassEntity classEntity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

}

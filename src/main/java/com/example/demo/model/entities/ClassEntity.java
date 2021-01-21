package com.example.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ClassEntity extends BaseEntity{

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classEntity")

    private List<StudentEntity> students = new ArrayList<>();


    @PreRemove
    public void setNullClassInStudentWhenDelete(){
        students.forEach(temp -> temp.setStatus(false));
//        auto set null class in student when delete class
    }
    @PreUpdate
    public void setNullClassInStudentWhenUpdate(){
        students.forEach(temp -> temp.setStatus(false));
//        auto set null class in student when delete class
    }
    @PostUpdate
    public void setClassInStudentWhenUpdate(){
//        System.out.println("post update!");
          students.forEach(temp -> temp.setAddress(this.getName()));
//          students.forEach(temp -> temp.setClassEntity(classEntity));
//        auto set class in student when delete class

    }


    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

}

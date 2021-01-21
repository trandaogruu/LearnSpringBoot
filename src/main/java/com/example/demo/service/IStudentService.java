package com.example.demo.service;


import com.example.demo.model.entities.StudentEntity;
import com.example.demo.model.bo.ClassStatusBo;
import com.example.demo.model.in.StudentEntityIn;

import java.util.List;


public interface IStudentService {

    StudentEntity create(StudentEntityIn studentEntityIn);

    StudentEntity update(StudentEntityIn studentEntityIn);

    String delete(int id);

    List<StudentEntity> findByClass(int idClass,int page, int limit);

    List<StudentEntity> findByClassAndStatus( ClassStatusBo classStatusBo);

    List<StudentEntity> findByClassIsNull(int page, int limit);
}

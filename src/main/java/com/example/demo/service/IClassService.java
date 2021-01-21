package com.example.demo.service;


import com.example.demo.model.entities.ClassEntity;
import com.example.demo.model.in.ClassEntityIn;

import java.util.List;


public interface IClassService {

    ClassEntity create(ClassEntityIn classEntityIn);

    ClassEntity update(ClassEntityIn classEntityIn);

    String delete(int id);

    List<ClassEntity> findAll(int page, int limit);

}

package com.example.demo.repository;


import com.example.demo.model.entities.ClassEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository  extends JpaRepository<ClassEntity,Integer> {

    @Query("SELECT c FROM ClassEntity c WHERE c.status = false AND c.id =:id")
    ClassEntity checkClassStatus(@Param("id") int id);

    @Query("SELECT c FROM ClassEntity c WHERE c.name =:name")
    List<ClassEntity> checkClassNameISExists(@Param("name") String name);
}

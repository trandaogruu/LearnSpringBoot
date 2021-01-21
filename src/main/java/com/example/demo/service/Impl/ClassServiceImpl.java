package com.example.demo.service.Impl;


import com.example.demo.model.entities.ClassEntity;
import com.example.demo.model.entities.StudentEntity;
import com.example.demo.model.in.ClassEntityIn;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.IClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassRepository classRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public ClassEntity create(ClassEntityIn classEntityIn) {
        ClassEntity classEntity = new ClassEntity();

//      checkNameIsExists
        if (this.checkNameIsExists(classEntityIn.getName())) {
            return classEntity;
        }
        BeanUtils.copyProperties(classEntityIn, classEntity);
        ClassEntity classEntitySaved = classRepo.save(classEntity);

//        add new student in class
        this.addStudentInClass(classEntitySaved, classEntityIn.getStudentEntities());

        return classEntitySaved;

    }

    @Override
    public ClassEntity update(ClassEntityIn classEntityIn) {

        ClassEntity classEntityOld = classRepo.findById(classEntityIn.getId()).orElse(null);
        if (classEntityOld != null) {

            //          bỏ liên kết student cũ
            if (this.checkNameIsExists(classEntityIn.getName())) {
                return new ClassEntity();
            }
            BeanUtils.copyProperties(classEntityIn, classEntityOld);

            ClassEntity classEntitySaved = classRepo.save(classEntityOld);

            //        add new student in class
            this.addStudentInClass(classEntitySaved, classEntityIn.getStudentEntities());

            return classEntitySaved;
        }
        return new ClassEntity();

    }

    @Override
    public String delete(int id) {

        ClassEntity classEntity = classRepo.findById(id).orElse(null);
        if (classEntity == null) return "not success";

//       xóa bỏ lớp học

        if (classRepo.checkClassStatus(id) != null) {
            classRepo.deleteById(id);
            return "Delete Success";
        } else {
            classEntity.setStatus(false);
            classRepo.save(classEntity);
            return "Update Status is Fasle";
        }

    }

    @Override
    public List<ClassEntity> findAll(int page, int limit) {
        return classRepo.findAll(PageRequest.of(page - 1, limit)).getContent();
    }


    private void addStudentInClass(ClassEntity entity, List<Integer> list) {
        list.stream().map(temp -> studentRepo.findById(temp).orElse(null))
                .filter(Objects::nonNull)
                .forEach(temp -> {
                    temp.setClassEntity(entity);
                    temp.setStatus(true);
                    studentRepo.save(temp);
                });
    }

    private boolean checkNameIsExists(String name) {
        return classRepo.checkClassNameISExists(name).size() > 0;
    }
}

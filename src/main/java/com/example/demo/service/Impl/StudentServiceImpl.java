package com.example.demo.service.Impl;

import com.example.demo.model.entities.ClassEntity;
import com.example.demo.model.entities.StudentEntity;
import com.example.demo.model.bo.ClassStatusBo;
import com.example.demo.model.in.StudentEntityIn;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ClassRepository classRepo;

    @Override
    public StudentEntity create(StudentEntityIn studentEntityIn) {
        StudentEntity studentEntity = new StudentEntity();

        BeanUtils.copyProperties(studentEntityIn,studentEntity);

        return this.saveStudent(studentEntity,studentEntityIn.getClassId());
    }

    @Override
    public StudentEntity update(StudentEntityIn studentEntityIn) {
        StudentEntity studentEntityOld = studentRepo.findById(studentEntityIn.getId()).orElse(null);

        if(studentEntityOld == null){
            return new StudentEntity();
        }

        BeanUtils.copyProperties(studentEntityIn,studentEntityOld);

       return this.saveStudent(studentEntityOld,studentEntityIn.getClassId());
    }

    @Override
    public String delete(int id) {
        StudentEntity  studentEntity = studentRepo.findById(id).orElse(null);
        if (studentEntity == null) return "not success";

        if (studentRepo.checkStudentStatus(id) != null) {
            studentRepo.deleteById(id);
            return "Delete Success";
        } else {
            studentEntity.setStatus(false);
           studentRepo.save(studentEntity);
            return "Update Status is Fasle";
        }

    }

    private StudentEntity saveStudent(StudentEntity entity, int sclass){
        ClassEntity classEntity = classRepo.findById(sclass).orElse(null);
        entity.setClassEntity(classEntity);
        return studentRepo.save(entity);
    }

    @Override
    public List<StudentEntity> findByClass(int classID,int page, int limit) {
        return studentRepo.findByClass(classID, PageRequest.of(page-1,limit)).getContent();
    }

    @Override
    public List<StudentEntity> findByClassAndStatus(ClassStatusBo classStatusBo) {
        return  studentRepo.findByClassAndStatus(classStatusBo.getIdClass(), classStatusBo.isStatus(),
                PageRequest.of(classStatusBo.getPage()-1, classStatusBo.getLimit())).getContent();
    }

    @Override
    public List<StudentEntity> findByClassIsNull(int page, int limit) {
        return studentRepo.findByClassIsNull(PageRequest.of(page,limit)).getContent();
    }


}


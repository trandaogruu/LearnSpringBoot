package com.example.demo.controller;

import com.example.demo.model.entities.StudentEntity;
import com.example.demo.model.bo.ClassStatusBo;
import com.example.demo.model.in.StudentEntityIn;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-management/student")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @PostMapping
    public StudentEntity create (@RequestBody StudentEntityIn studentEntityIn){
        return iStudentService.create(studentEntityIn);
    }

    @PutMapping
    public StudentEntity update (@RequestBody StudentEntityIn studentEntityIn){
        return iStudentService.update(studentEntityIn);
    }

    @DeleteMapping
    public String delete (@RequestParam int idClass){
       return iStudentService.delete(idClass);
    }

    @GetMapping(value = "/class/{idClass}")
    public List<StudentEntity> findAllByClass(  @PathVariable(value = "idClass") int idClass,
                                                @RequestParam(value = "page")int page,
                                                @RequestParam(value = "limit") int limit){
        return iStudentService.findByClass(idClass,page,limit);
    }
    @GetMapping(value = "/class/{idClass}/status/{status}")
    public List<StudentEntity> findAllByClassAndStatus(
                                              @PathVariable(value = "idClass") int idClass,
                                              @PathVariable(value = "status") boolean status,
                                              @RequestParam(value = "page")int page,
                                              @RequestParam(value = "limit") int limit){

        ClassStatusBo classStatusBo = new ClassStatusBo(idClass,status,page,limit);
        return iStudentService.findByClassAndStatus(classStatusBo);
    }
    @GetMapping(value = "/class/is-null")
    public List<StudentEntity> findByClassIsNull(
                                        @RequestParam(value = "page")int page,
                                        @RequestParam(value = "limit") int limit){
        return iStudentService.findByClassIsNull(page,limit);
    }
}

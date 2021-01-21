package com.example.demo.controller;

import com.example.demo.model.entities.ClassEntity;
import com.example.demo.model.in.ClassEntityIn;
import com.example.demo.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-management/class")
public class ClassController {

    @Autowired
    private IClassService iClassService;

    @PostMapping
    public ClassEntity create (@RequestBody ClassEntityIn classEntityIn){
        return iClassService.create(classEntityIn);
    }

    @PutMapping
    public ClassEntity update (@RequestBody ClassEntityIn classEntityIn){
        return iClassService.update(classEntityIn);
    }

    @DeleteMapping(value = "/{class-id}")
    public String delete (@PathVariable(value = "class-id") int id){
        return iClassService.delete(id);
    }

    @GetMapping
    public List<ClassEntity> findAll(@RequestParam(value = "page")int page,
                                     @RequestParam(value = "limit") int limit){
        return iClassService.findAll(page,limit);
    }
}

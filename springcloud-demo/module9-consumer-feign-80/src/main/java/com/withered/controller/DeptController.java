package com.withered.controller;

import com.withered.pojo.Dept;
import com.withered.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
class DeptController {
    @Autowired
    private DeptClientService service;

    @RequestMapping("/save")
    public boolean save(Dept dept) {
        return service.save(dept);
    }

    @RequestMapping("/get/{id}")
    public Dept getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @RequestMapping("/list")
    public List<Dept> list() {
        return service.list();
    }
}

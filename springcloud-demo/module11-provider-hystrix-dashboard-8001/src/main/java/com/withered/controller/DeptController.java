package com.withered.controller;


import com.withered.pojo.Dept;
import com.withered.service.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提供 restful 服务
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptServiceImpl deptService;

    @PostMapping("/save")
    public boolean save(Dept dept) {
        return deptService.save(dept);
    }

    @GetMapping("/get/{id}")
    public Dept getById(@PathVariable("id") Long id) {
        return deptService.getById(id);
    }

    @GetMapping("/list")
    public List<Dept> list() {
        return deptService.list();
    }
}

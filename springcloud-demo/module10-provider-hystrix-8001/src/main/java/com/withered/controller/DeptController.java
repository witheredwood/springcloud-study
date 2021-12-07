package com.withered.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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


//    /**
//     * 异常处理方法
//     *
//     * @param id 部门id
//     * @return 该部门id的所有信息
//     */
//    @GetMapping("/get/{id}")
//    public Dept getById(@PathVariable("id") Long id) {
//        Dept dept = deptService.getById(id);
//        if (dept == null) {
//            throw new RuntimeException("id => " + id + " 不存在");
//        }
//        return dept;
//    }

    /**
     * 服务熔断处理方法
     *
     * @param id 部门id
     * @return 该部门id的所有信息
     */
    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "getHystrix")
    public Dept getById(@PathVariable("id") Long id) {
        Dept dept = deptService.getById(id);
        if (dept == null) {
            throw new RuntimeException("id => " + id + " 不存在");
        }
        return dept;
    }

    public Dept getHystrix(Long id) {
        return new Dept()
                .setDeptno(id)
                .setDname("id => " + id + " 不存在")
                .setDb_source("不存在该数据库");
    }

}

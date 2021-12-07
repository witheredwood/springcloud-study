package com.withered.service;


import com.withered.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {
    @PostMapping("/consumer/dept/save")
    boolean save(Dept dept);

    @GetMapping("/consumer/dept/get/{id}")
    Dept getById(@PathVariable("id") Long id);

    @GetMapping("/consumer/dept/list")
    List<Dept> list();
}

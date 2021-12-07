package com.withered.service;

import com.withered.pojo.Dept;

import java.util.List;

public interface DeptService {
    boolean save(Dept dept);

    Dept getById(Long id);

    List<Dept> list();
}

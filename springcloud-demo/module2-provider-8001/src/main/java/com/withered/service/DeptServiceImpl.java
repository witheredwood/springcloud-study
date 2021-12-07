package com.withered.service;

import com.withered.dao.DeptDao;
import com.withered.pojo.Dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean save(Dept dept) {
        return deptDao.save(dept);
    }

    @Override
    public Dept getById(Long id) {
        return deptDao.getById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDao.list();
    }
}

package com.withered.dao;

import com.withered.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {
    boolean save(Dept dept);

    Dept getById(Long id);

    List<Dept> list();
}

package com.withered.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 所有的实体类必须实现序列化
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)  // 开启链式写法
public class Dept implements Serializable {
    private Long deptno; // 主键
    private String dname;
    private String db_source; // 该数据存在哪个数据库的字段

    public Dept(String dname) {
        this.dname = dname;
    }
}

package com.withered.controller;

import com.withered.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer/dept")
class DeptController {

    // RestTemplate：直接调用，注册到Spring中
    @Autowired
    private RestTemplate restTemplate;  // 提供多种便捷访问远程http服务的方法，简单的restful服务模板
//     非负载均衡
//    private static final String REST_URL_SUFFIX = "http://localhost:8001";
    // 负载均衡
    private static final String REST_URL_SUFFIX = "http://springcloud-provider-dept";


    @RequestMapping("/save")
    public boolean save(Dept dept) {
        // （url, 实体，返回类型的class）
        return restTemplate.postForObject(REST_URL_SUFFIX + "/dept/save", dept, boolean.class);
    }

    @RequestMapping("/get/{id}")
    public Dept getById(@PathVariable("id") Long id) {
        // （url, 实体，返回类型的class）
        return restTemplate.getForObject(REST_URL_SUFFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/list")
    public List<Dept> list() {
        // （url, 实体，返回类型的class）
        return restTemplate.getForObject(REST_URL_SUFFIX + "/dept/list", List.class);
    }
}

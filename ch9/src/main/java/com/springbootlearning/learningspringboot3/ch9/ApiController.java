package com.springbootlearning.learningspringboot3.ch9;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @RestController, Spring Web 注解, 表示该控制器涉及的是数据, 而不是模版
// @GetMapping, 将 HTTP Get /api/employees Web 调用映射到该方法上
// Flux<Employee>, 返回类型是 Employee 的 Flux 记录
@RestController
public class ApiController {
    @GetMapping("/api/employees")
    Flux<Employee> employees() {
        return Flux.just(
            new Employee("alice", "management"),
            new Employee("bob", "payroll")
        );
    }

    public static Map<String, Employee> DATABASE = new LinkedHashMap<>();

    // @PostMapping, HTTP POST /api/employees Web 调用的映射方法
    // @RequestBody, 让 Spring Web 将传入的 HTTP 请求正文反序列化为 Employee 数据类型
    // Mono<Employee>, Reactor 对单个项目 Flux 的替代
    // DATABASE, 临时数据存储 (Java Map)
    // 传入的数据被包装在 Reactor Mono 中    
    @PostMapping("/api/employees")
    Mono<Employee> add(@RequestBody Mono<Employee> newEmployee) {
        return newEmployee.map(employee -> {
            DATABASE.put(employee.name(), employee);
            return employee;
        });
    }

    
}

package com.springbootlearning.learningspringboot3.ch9;

import static com.springbootlearning.learningspringboot3.ch9.ApiController.DATABASE;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;

// 响应式 Web 控制器
// @Controller, 表示该类包含渲染模版的 Web 方法
// @GetMapping, 用于将 GET/Web 调用映射到此方法
// Mono<Rendering>, Mono 是 Reactor 的单值响应类型. Rendering 是 Spring Web Flux 的值类型, 允许传递要显示的视图名称和模型属性
// Flux.fromIterable(), 该静态辅助方法允许包装任何 Java Iterable, 然后使用我们的响应式 API
// DATABASE.values, 临时数据源
// collectList(), 该 Flux 方法允许将项目流收集到 Mono<List<Employee>> 中
// map(), 此操作让我们可以访问 Mono 中的列表, 然后将其转换为 Rendering,. 我们希望显示的
//   视图名称是 indiex, 还可以使用在此 Mono 中找到的值加载模型 employee 属性
// build(), Rendering 是一个构建器构造, 因此这是将所有部分转换为最终不可变实例的步骤. 重要的要了解
//   在 map() 操作内部, 输出的是 Mono<Rendering>
@Controller
public class HomeController {
    @GetMapping("/")
    Mono<Rendering> index() {
        return Flux.fromIterable(DATABASE.values())
          .collectList()
          .map(employees -> Rendering
            .view("index")
            .modelAttribute("employees", employees)
            .modelAttribute("newEmployee", new Employee("", ""))
            .build());
    }

    @PostMapping("/new-employee")
    Mono<String> newEmployee(@ModelAttribute Mono<Employee> newEmployee) {
        return newEmployee
          .map(employee -> {
            DATABASE.put(employee.name(), employee);
            return "redirect:/";
          });
    }
    
}

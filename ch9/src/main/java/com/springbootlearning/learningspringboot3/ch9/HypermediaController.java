package com.springbootlearning.learningspringboot3.ch9;

import static com.springbootlearning.learningspringboot3.ch9.ApiController.DATABASE;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// @RestController, Spring Web annotation, 用于将此控制器标记为专注于 JSON 序列化而不是模版显示
// @EnableHypermediaSupport, Spring HATEOAS annotation, 用于激活超媒体支持, 本例为 HAL support
//   如果使用 Spring Boot Starter HATEOAS, 则 HAL 支持将自动激活. 如果式手动插入 Spring HATEOAS, 必须自己激活
//
// @GetMapping, HTTP GET/hypermedia/employee/{key} 方法
// 返回类型是 Mono<EntityModel<Employee>>
@RestController
@EnableHypermediaSupport(type = HAL)
public class HypermediaController {

    @GetMapping("/hypermedia/employees/{key}")
    Mono<EntityModel<Employee>> employee(@PathVariable String key) {
        Mono<Link> selfLink = linkTo( // 
            methodOn(HypermediaController.class) //
              .employee(key)) //
                .withSelfRef() //
                .toMono();

        Mono<Link> aggregateRoot = linkTo(
            methodOn(HymediaController.class)
              .employees())
                .withRel(LinkRelation.of("employees"))
                .toMono();

        Mono<Tuple2<Link, Link>> links = Mono.zip(selfLink, aggregateRoot);

        return links.map(objects -> EntityModel.of(DATABASE.get(key), objects.getT1(), objects.getT2()));
    }

    @GetMapping("/hypermedia/employees")
    Mono<CollectionModel<EntityModel<Employee>>> employees() {
        Mono<Link> selfLink = linkTo(
            methodOn(HypermediaController.class)
              .employees())
                .withSelfRel()
                .toMono();

      return selfLink.flatMap(self -> Flux.fromIterable(DATABASE.keySet())
          .flatMap(key -> employee(key))
          .collectList()
          .map(entityModels -> CollectionModel.of(entityModels, self)));
    }
    
}

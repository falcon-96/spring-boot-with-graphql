package playground.graphql.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import playground.graphql.model.Animal;
import playground.graphql.service.ZooService;

import java.util.List;

@Controller
public class ZooController {

    private final ZooService zooService;

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }

    @QueryMapping
    Animal animalById(@Argument Long id) {
        return zooService.findAnimalById(id);
    }

    @QueryMapping
    List<Animal> animals() {
        return zooService.findAllAnimals();
    }
}

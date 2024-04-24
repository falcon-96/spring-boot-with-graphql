package playground.graphql.service;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import playground.graphql.model.Animal;
import playground.graphql.model.Creature;

import java.util.List;

@Service
public class ZooService {

    private final JdbcClient jdbcClient;


    public ZooService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Animal> findAllAnimals() {
        String animalSql = "SELECT * FROM ZOO";
        return jdbcClient.sql(animalSql)
                .query(Animal.class)
                .list();
    }

    public Animal findAnimalById(Long id) {
        return jdbcClient.sql("SELECT * FROM ZOO WHERE ID = ?")
                .param(id)
                .query(Animal.class)
                .single();
    }

    public Animal addAnimalToZoo(Creature creature) {
        jdbcClient.sql("INSERT INTO ZOO(ID, NAME) VALUES(?, ?)")
                .param(creature.id())
                .param(creature.name())
                .update();

        return findAnimalById(creature.id());
    }
}

package service;

import model.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MyServiceImpl implements MyService {

    @PostConstruct
    public void init() {
        // Setup database connection
    }

    @PreDestroy
    public void tearDown() {
        // Close  database connection
    }

    @Override
    public List<Person> getPersons() {
        List<Person> lPersons = new ArrayList<>();
        Person pes1 = new Person(1L, "João da Silva");
        Person pes2 = new Person(2L, "Maria da Conceição");
        lPersons.add(pes1);
        lPersons.add(pes2);
        return lPersons;
    }
}

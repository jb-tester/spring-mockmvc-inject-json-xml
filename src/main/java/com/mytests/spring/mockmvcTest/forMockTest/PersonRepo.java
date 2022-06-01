package com.mytests.spring.mockmvcTest.forMockTest;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * *
 * <p>Created by irina on 10/20/2021.</p>
 * <p>Project: spring-mockmvc-inject-json-xml</p>
 * *
 */
@Repository
public class PersonRepo {
    public List<Person> db = new ArrayList();
    public void save(Person person) {
        db.add(person);
    }

    public List<Person> getPersons() {
        return db;
    }

    public Person getById(String id) {
        Person rez = null;
        for (Person person : db) {
            if(Objects.equals(person.getId(), id)){rez = person;}
        }
        return rez;
    }
}

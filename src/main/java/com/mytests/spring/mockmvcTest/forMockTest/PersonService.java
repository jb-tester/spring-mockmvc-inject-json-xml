package com.mytests.spring.mockmvcTest.forMockTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * *
 * <p>Created by irina on 10/20/2021.</p>
 * <p>Project: spring-jsonpath-test0</p>
 * *
 */
@Service
public class PersonService {


    @Autowired
    private PersonRepo personRepo;

    public String savePerson(Person person){
        personRepo.save(person);
        return person.getId();
    }

    public List<Person> getPersons() {
        return personRepo.getPersons();
    }

    public Person getById(String id){
        return personRepo.getById(id);
    }
}

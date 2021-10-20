package com.mytests.spring.mockmvcTest.forMockTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * *
 * <p>Created by irina on 10/20/2021.</p>
 * <p>Project: spring-jsonpath-test0</p>
 * *
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/createPerson")
    public Person createNewPerson(@RequestBody Person person, HttpServletResponse httpResponse, WebRequest request) {
        String id = personService.savePerson(person);
        person.setId(id);
        httpResponse.setHeader("pid", String.format("%s/person/%s",
                request.getContextPath(), id));
        httpResponse.setStatus(HttpStatus.CREATED.value());
        return person;
    }
    @GetMapping("/allPersons")
    public List<Person> getAll(){
        return personService.getPersons();
    }
}

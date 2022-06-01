package com.mytests.spring.mockmvcTest;

import com.mytests.spring.mockmvcTest.forMockTest.Person;
import com.mytests.spring.mockmvcTest.forMockTest.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ SpringMockmvcInjectJsonXmlApplication.class })
public class SpringMockmvcInjectJsonXmlApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    @MockBean
    PersonService service;

    @Test
    public void shouldCreatePersonJson() throws Exception {

        when(service.savePerson(any(Person.class))).thenReturn("200");

        mockMvc.perform(post("/createPerson")
                        .contentType(MediaType.APPLICATION_JSON) // ok
                        //.contentType(MediaType.APPLICATION_JSON_VALUE) // injection doesn't work in this case
                        //.contentType("application/json")   // injection doesn't work in this case
                        .content("{\n" +
                                "  \"name\": \"masha\",\n" +
                                "  \"familyName\": \"ivanova\",\n" +
                                "  \"age\": 25\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("pid", "/person/200"))
                .andExpect(content().json("{ \"id\": \"200\",\"name\": \"masha\", \"familyName\": \"ivanova\", \"age\": 25 }", true))
                .andExpect(jsonPath("$.id").value("200"))
                .andExpect(jsonPath("$.name").value("masha"))
                .andExpect(jsonPath("$.familyName").value("ivanova"))
                .andExpect(jsonPath("$.age").value(25));
    }
    @Test
    public void shouldCreatePersonXML() throws Exception {
        String xml = "<person>\n" +
                "    <name>vasya</name>\n" +
                "    <familyName>petrov</familyName>\n" +
                "    <age>25</age>\n" +
                "</person>";
        when(service.savePerson(any(Person.class))).thenReturn("100");

        mockMvc.perform(post("/createPerson")
                        .contentType(MediaType.APPLICATION_XML)  // ok
                        //.contentType("application/xml") // injection doesn't work in this case
                        //.content(xml.getBytes(StandardCharsets.UTF_8))  // injection doesn't work in this case
                        // .content(xml)  // no injection to 'xml' variable value
                        .content("<person>\n" +
                                "    <name>vasya</name>\n" +
                                "    <familyName>petrov</familyName>\n" +
                                "    <age>25</age>\n" +
                                "</person>")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(header().string("pid", "/person/100"))
                .andExpect(xpath("/Person/name").string("vasya"))  // inject xpath here
                .andExpect(content().string("<Person><id>100</id><name>vasya</name><familyName>petrov</familyName><age>25</age></Person>"))
                .andExpect(content().xml("<Person><id>100</id><name>vasya</name><familyName>petrov</familyName><age>25</age></Person>"));

    }



}

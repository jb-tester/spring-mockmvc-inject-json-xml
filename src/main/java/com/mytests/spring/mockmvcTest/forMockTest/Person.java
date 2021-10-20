package com.mytests.spring.mockmvcTest.forMockTest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * *
 * <p>Created by irina on 10/20/2021.</p>
 * <p>Project: spring-jsonpath-test0</p>
 * *
 */
@XmlRootElement
public class Person {
    String id;
    String name;
    String familyName;
    int age;

    public Person() {
    }

    public Person(String id, String name, String familyName, int age) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.age = age;
    }

    public Person(String name, String familyName, int age) {
        this.name = name;
        this.familyName = familyName;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
@XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    @XmlElement
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

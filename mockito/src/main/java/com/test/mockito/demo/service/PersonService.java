package com.test.mockito.demo.service;

import com.test.mockito.demo.dao.PersonDao;
import com.test.mockito.demo.entity.Person;

public class PersonService {

    private PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void update(int id,String name){
        personDao.update(new Person(id,name));
    }
}

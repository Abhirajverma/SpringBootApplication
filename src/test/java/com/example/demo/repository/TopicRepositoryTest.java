package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class TopicRepositoryTest{

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TopicRepository topicRepository;

    @Test
    void context() {

        Topics topic=new Topics("Java","Java  Fundamentals","Java Description");
        Topics saveInDb=entityManager.persist(topic);
        Optional<Topics> t=topicRepository.findById(saveInDb.getId());
        Topics getFromDb=t.get();
        assertEquals(saveInDb,getFromDb);

    }

}

package com.example.demo.db.repository;

import com.example.demo.db.entity.Topics;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
class TopicRepositoryTest{


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TopicRepository topicRepository;

    @Test
    void findByIdtest() {

        Topics topic=new Topics(1L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        Topics saveInDb=entityManager.persist(topic);
        Optional<Topics> t=topicRepository.findById(saveInDb.getId());
        Topics getFromDb=t.get();
        assertEquals(saveInDb,getFromDb);
    }

    @Test
    void findByAllTest()
    {
        Topics topic1=new Topics(1L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        entityManager.persist(topic1);
        entityManager.persist(topic2);
        List<Topics> topic=new ArrayList<>(topicRepository.findAll());
        assertEquals(topic.size(),2);
    }

    @Test
    void DeleteTest()
    {
        Topics topic1=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        entityManager.persist(topic1);
        entityManager.persist(topic2);
        topicRepository.deleteById(topic2.getId());
        List<Topics> topic=new ArrayList<>(topicRepository.findAll());
        assertEquals(topic.size(),1);
    }

    @Test
    void PostTest()
    {
        Topics topic1=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        topicRepository.save(topic1);
        topicRepository.save(topic2);
        List<Topics> topic=new ArrayList<>(topicRepository.findAll());
        assertEquals(topic.size(),2);
    }

    @Test
    void putTest()
    {
        Topics topic1=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        entityManager.persist(topic1);
        entityManager.persist(topic2);
        Topics new_topic =new Topics(1L,"Blockchain","Blockchain Description", LocalDateTime.now(),LocalDateTime.now());
        Topics existingTopic=topicRepository.getOne(1L);
        BeanUtils.copyProperties(new_topic,existingTopic,"id");
        topicRepository.save(existingTopic);
        List<Topics> topic=new ArrayList<>(topicRepository.findAll());
        assertEquals(topic.size(),2);
        for(Topics t:topic)
        {
          System.out.println(t.getId()+" "+t.getName()+" "+t.getDescription());
        }

    }


}

package com.example.demo.service;

import com.example.demo.db.entity.Topics;
import com.example.demo.db.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class TopicServiceTest {

    @Autowired
    private TopicService topicservice;

    @MockBean
    private TopicRepository topicRepository;

    @Test
    void getAllTopicsTest()
    {
        Topics topic1=new Topics(1L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        List<Topics> topicList=new ArrayList<>();
        topicList.add(topic1);
        topicList.add(topic2);
        Mockito.when(topicRepository.findAll()).thenReturn(topicList);
        assertEquals(topicservice.getAllTopics().size(),2);
        assertEquals(topicservice.getAllTopics(),topicList);
    }

    @Test
    void getTopicByIdTest()
    {
        Topics actualTopic=new Topics(1L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicRepository.findById(1L)).thenReturn(Optional.of(actualTopic));
        Topics new_topic=topicservice.getTopicById(1L);
        assertEquals(new_topic,actualTopic);
    }

    @Test
    void getTopicByNameTest()
    {
        Topics actualTopic=new Topics(1L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicRepository.findByName("Java")).thenReturn(Optional.of(actualTopic));
        Topics new_topic=topicservice.getTopicByName("Java");
        assertEquals(new_topic,actualTopic);
    }

    @Test
    void addTopicTest()
    {
        Topics actualTopic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicRepository.saveAndFlush(actualTopic)).thenReturn(actualTopic);
        assertEquals(topicservice.addTopic(actualTopic),actualTopic);
    }

    @Test
    void updateTopicTest()
    {
        Topics topic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicRepository.existsById(1L)).thenReturn(true);
        Mockito.when(topicRepository.getOne(1L)).thenReturn(topic);
        Topics updateTopic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        BeanUtils.copyProperties(updateTopic,topic,"id");
        Mockito.when(topicRepository.saveAndFlush(topic)).thenReturn(topic);
        assertEquals(topicservice.updateTopic(1L,updateTopic),topic);
    }

    @Test
    void DeleteTopicByIdTest()
    {
        Topics topic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(topicRepository).deleteById(1L);
        assertNull(topicservice.deleteTopicById(1L));
    }

    @Test
    void DeleteTopicByNameTest()
    {
        Topics topic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicRepository.existsByName("Javascript")).thenReturn(true);
        Mockito.doNothing().when(topicRepository).deleteByName("Javascript");
        assertNull(topicservice.deleteTopicByName("Javascript"));
    }

}

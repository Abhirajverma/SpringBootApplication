package com.example.demo.controller;

import com.example.demo.db.entity.Topics;
import com.example.demo.service.TopicService;
import com.example.demo.utils.Methods;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @Test
    public void testMyList() throws Exception {

        Topics topic1=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        List<Topics> topicsList=new ArrayList<>();
        topicsList.add(topic1);
        topicsList.add(topic2);
        Mockito.when(topicService.getAllTopics()).thenReturn(topicsList);
        mockMvc.perform(get("/topics/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(topic1.getId()))
                .andExpect(jsonPath("$[0].name").value(topic1.getName()))
                .andExpect(jsonPath("$[0].description").value(topic1.getDescription()));

    }

    @Test
    public void testGetTopic() throws Exception {

        Topics topic1=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Topics topic2=new Topics(2L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
        List<Topics> topicsList=new ArrayList<>();
        topicsList.add(topic1);
        topicsList.add(topic2);
        Mockito.when(topicService.getTopicById(1L)).thenReturn(topicsList.get(0));
        mockMvc.perform(get("/topics/getById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(topic1.getId()))
                .andExpect(jsonPath("$.name").value(topic1.getName()))
                .andExpect(jsonPath("$.description").value(topic1.getDescription()));

    }

    @Test
    public void testAddTopic() throws Exception {

        Topics topic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicService.addTopic(Mockito.any(Topics.class))).thenReturn(topic);
        String jsonTopic=Methods.mapToJson(topic);
        mockMvc.perform(post("/topics/addTopic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTopic)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(topic.getId()))
                .andExpect(jsonPath("$.name").value(topic.getName()))
                .andExpect(jsonPath("$.description").value(topic.getDescription()));

    }

    @Test
    public void testUpdateTopic() throws Exception {

        Topics topic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicService.updateTopic(1L,topic)).thenReturn(topic);
        String jsonTopic= Methods.mapToJson(topic);
        mockMvc.perform(put("/topics/updateTopic/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTopic)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(topic.getId()))
                .andExpect(jsonPath("$.name").value(topic.getName()))
                .andExpect(jsonPath("$.description").value(topic.getDescription()));

    }

    @Test
    public void testDeleteTopicById() throws Exception {

        Topics topic=new Topics(1L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
        Mockito.when(topicService.deleteTopicById(1L)).thenReturn(topic);
        mockMvc.perform(delete("/topics/deleteTopicById/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(topic.getId()))
                .andExpect(jsonPath("$.name").value(topic.getName()))
                .andExpect(jsonPath("$.description").value(topic.getDescription()));

    }






}









package com.example.demo.service;

import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topics> getTopics() {
        List<Topics> topic=new ArrayList<>();
        topicRepository.findAll().forEach(topic::add);
        return topic;
    }
    public Topics getTopic(String id)
    {
       Object t= topicRepository.findById(id);
       return (Topics)t;
    }

    public void addTopic(Topics topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topics topic) {
      topicRepository.save(topic);
      if(!(topic.getId().equals(id)))
      {
          topicRepository.deleteById(id);
      }
    }
    public void deleteTopic(String id) {
      topicRepository.deleteById(id);
    }


    public boolean checkId(String id) {
        List<Topics> topic=new ArrayList<>();
        topicRepository.findAll().forEach(topic::add);
       for(Topics t:topic)
           if(t.getId().equals(id))
               return true;
           return false;
    }
}


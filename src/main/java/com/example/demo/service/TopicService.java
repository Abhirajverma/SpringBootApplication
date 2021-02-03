package com.example.demo.service;

import com.example.demo.repository.Confirmation;
import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Topics> t=topicRepository.findById(id);
        return t.get();


    }

    public Confirmation addTopic(Topics topic) {
        topicRepository.save(topic);
        return new Confirmation(topic,"Post Operation Successful");
    }

    public Confirmation updateTopic(String id, Topics topic) {
      topicRepository.save(topic);
      if(!(topic.getId().equals(id)))
      {
          topicRepository.deleteById(id);
      }
        return new Confirmation(topic,"Put Operation Successful");
    }
    public Confirmation deleteTopic(String id) {
        Topics topic=getTopic(id);
        topicRepository.deleteById(id);
        return new Confirmation(topic,"Delete Operation Successful");

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


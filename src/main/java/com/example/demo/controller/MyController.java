package com.example.demo.controller;

import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.Topics;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private TopicService topicService;
    @RequestMapping("/topics")
    public List<Topics> MyList()
    {
        return topicService.getTopics();
    }
    @RequestMapping("/topics/{id}")
    public Topics getTopic(@PathVariable String id)
    {
        return topicService.getTopic(id);
    }
    @RequestMapping(method= RequestMethod.POST,value="/topics")
    public void addTopic(@RequestBody Topics topic)
    {
        topicService.addTopic(topic);
    }
    @RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
    public void UpdateTopic(@RequestBody Topics topic,@PathVariable String id)
    {
        if(topicService.checkId(id))
        topicService.updateTopic(id,topic);
    }
    @RequestMapping(method=RequestMethod.DELETE,value="/topics/{id}")
    public void deleteTopics(@PathVariable String id)
    {
        topicService.deleteTopic(id);
    }


}

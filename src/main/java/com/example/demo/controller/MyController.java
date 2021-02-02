package com.example.demo.controller;

import com.example.demo.repository.Confirmation;
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
    public Confirmation addTopic(@RequestBody Topics topic)
    {
        return topicService.addTopic(topic);

    }
    @RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
    public Confirmation UpdateTopic(@RequestBody Topics topic,@PathVariable String id)
    {
        if(topicService.checkId(id))
        return topicService.updateTopic(id,topic);
    return new Confirmation(null,"Not Successful Operation as id Not Exist");
    }
    @RequestMapping(method=RequestMethod.DELETE,value="/topics/{id}")
    public Confirmation deleteTopics(@PathVariable String id)
    {
        return topicService.deleteTopic(id);
    }


}

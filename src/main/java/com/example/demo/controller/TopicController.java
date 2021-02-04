package com.example.demo.controller;

import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.db.entity.Topics;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/getAll")
    public List<Topics> MyList()
    {
        return topicService.getAllTopics();
    }

    @GetMapping("/getById/{id}")
    public Topics getTopic(@PathVariable Long id)
    {
        return topicService.getTopic(id);
    }

    @GetMapping("/getByName/{name}")
    public Topics getTopicByName(@PathVariable String name) { return topicService.getTopicByName(name); }


    @PostMapping("/addTopic")
    public Topics addTopic(@RequestBody Topics topic) { return topicService.addTopic(topic); }

    @PutMapping("/updateTopic/{id}")
    public Topics UpdateTopic(@RequestBody Topics topic,@PathVariable Long id) { return topicService.updateTopic(id,topic); }

    @DeleteMapping("/deleteTopicById/{id}")
    public Topics deleteTopicsById(@PathVariable Long id)
    {
        return topicService.deleteTopicById(id);
    }

    @DeleteMapping("/deleteTopicByName/{name}")
    public Topics deleteTopicByName(@PathVariable String name) { return topicService.deleteTopicByName(name); }
}

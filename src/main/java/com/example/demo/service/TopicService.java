package com.example.demo.service;

import com.example.demo.db.repository.TopicRepository;
import com.example.demo.db.entity.Topics;
import com.example.demo.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicService {

    private static final Logger logger= LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private TopicRepository topicRepository;

    public List<Topics> getAllTopics()
    {
        List<Topics> topic=new ArrayList<>(topicRepository.findAll());
        logger.info(Constants.SUCCESS);
        return topic;
    }

    public Topics getTopic(Long id)
    {
        Optional<Topics> topic = topicRepository.findById(id);
        if(topic.isPresent())
        {
            logger.info(Constants.SUCCESS);
            return topic.get();
        }
        else
        {
            logger.warn(Constants.FAILURE);
            return null;
        }
    }

    public Topics getTopicByName(String name)
    {
      Optional<Topics> topic = topicRepository.findByName(name);
      if(topic.isPresent())
      {
       logger.info(Constants.SUCCESS);
       return topic.get();
      }
     else
     {
      logger.warn(Constants.FAILURE);
      return null;
     }
    }

   public Topics addTopic(Topics topic)
   {
        topicRepository.save(topic);
        logger.info(Constants.SUCCESS);
        return topic;
   }

   public Topics updateTopic(Long id, Topics topic)
    {
        if(topicRepository.existsById(id))
        {
          Topics existingTopic=topicRepository.getOne(id);
          BeanUtils.copyProperties(topic,existingTopic,"id");
          topic=topicRepository.saveAndFlush(existingTopic);
          logger.info(Constants.SUCCESS);
        }
        else
        {
           logger.warn(Constants.FAILURE);
           topic=null;
        }
      return topic;
    }

    public Topics deleteTopicById(Long id)
    {
        if (topicRepository.existsById(id))
       {
           topicRepository.deleteById(id);
           logger.info(Constants.SUCCESS);
       }
       else
       {
           logger.warn(Constants.FAILURE);
       }
       return null;
    }

  public Topics deleteTopicByName(String name)
  {
    if (topicRepository.existsByName(name))
    {
      topicRepository.deleteByName(name);
      logger.info(Constants.SUCCESS);
    }
    else
    {
      logger.warn(Constants.FAILURE);
    }
    return null;
  }

}


package com.example.demo.controller;

import com.example.demo.TopicApplication;
import com.example.demo.db.entity.Topics;
import com.example.demo.utils.Constants;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDateTime;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TopicApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicControllerIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  private final HttpHeaders header=new HttpHeaders();

  @Test
  public void testGetTopic() throws Exception
  {
    ResponseEntity<Topics> response=testRestTemplate.getForEntity(Constants.BASE + port + "/topics/getById/9"
                                                   , Topics.class);
    Topics responseBody=response.getBody();
    assert responseBody != null;
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(9L, responseBody.getId());
    assertEquals("Java", responseBody.getName());
    assertEquals("Java Description", responseBody.getDescription());

  }

  @Test
  public void testAddTopic() throws Exception
  {
    Topics topic=new Topics(10L,"SpringBoot","SpringBoot Description", LocalDateTime.now(),LocalDateTime.now());
    HttpEntity<Topics> entity=new HttpEntity<>(topic,header);
    ResponseEntity<Topics> response=testRestTemplate.exchange(Constants.BASE+port+"/topics/addTopic"
                                                              ,HttpMethod.POST,entity,Topics.class);
    Topics responseBody=response.getBody();
    assert responseBody != null;
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(topic.getId(), responseBody.getId());
    assertEquals(topic.getName(), responseBody.getName());
    assertEquals(topic.getDescription(), responseBody.getDescription());
  }

  @Test
  public void testUpdateTopic() throws Exception
  {
    Topics topic=new Topics(9L,"Java","Java Description", LocalDateTime.now(),LocalDateTime.now());
    HttpEntity<Topics> entity=new HttpEntity<>(topic,header);
    ResponseEntity<Topics> response=testRestTemplate.exchange(Constants.BASE+port+"/topics/updateTopic/9"
            ,HttpMethod.PUT,entity,Topics.class);
    Topics responseBody=response.getBody();
    assert responseBody != null;
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(topic.getId(), responseBody.getId());
    assertEquals(topic.getName(), responseBody.getName());
    assertEquals(topic.getDescription(), responseBody.getDescription());
    assertEquals(topic.getCreate_timestamp(), responseBody.getCreate_timestamp());
    System.out.println(responseBody);
  }

  @Test
  public void testDeleteTopicById() throws Exception
  {
    testRestTemplate.delete(Constants.BASE+port+"/topics/deleteTopicById/8");
  }

}

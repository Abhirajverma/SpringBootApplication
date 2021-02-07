package com.example.demo.controller;

import com.example.demo.TopicApplication;
import com.example.demo.db.entity.Topics;
import com.example.demo.utils.Methods;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TopicApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicControllerIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  private final HttpHeaders header=new HttpHeaders();

  @Test
  public void testAddTopic() throws Exception
  {
    Topics topic=new Topics(16L,"Javascript","Javascript Description", LocalDateTime.now(),LocalDateTime.now());
    String jsonTopic= Methods.mapToJson(topic);
    HttpEntity<Topics> entity=new HttpEntity<>(topic,header);
    ResponseEntity<String> response=testRestTemplate.exchange(Methods.fullUrlWithPort(port,"/topics/addTopic"),
                                                              HttpMethod.POST,entity,String.class);
    assertEquals(jsonTopic,response.getBody());
  }


}

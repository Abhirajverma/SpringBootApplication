package com.example.demo.db.repository;

import com.example.demo.db.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topics,Long> {

     Optional<Topics> findById(Long Id);
     Optional<Topics> findByName(String name);
     boolean existsByName(String name);
     void deleteByName(String name);

}

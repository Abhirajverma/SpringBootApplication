package com.example.demo.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="topics")
public class Topics {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id",updatable = false,nullable = false)
  private Long id;

  @Column(name="name")
  private String name;

  @Column(name="description")
  private String description;

  @CreationTimestamp
  @Column(name="creation_timestamp",nullable = false,updatable = false)
  private LocalDateTime create_timestamp;

  @UpdateTimestamp
  @Column(name="updated_timestamp",nullable = false)
  private LocalDateTime update_timestamp;

}

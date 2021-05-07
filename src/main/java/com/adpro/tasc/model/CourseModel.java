package com.adpro.tasc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter @Setter @NoArgsConstructor
public class CourseModel implements Serializable {

  @Id
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "course")
  private Set<CourseListModel> courseList = new HashSet<>();

}

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
@Table(name = "course_list")
@Getter @Setter @NoArgsConstructor
public class CourseListModel implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "username")
  private UserModel user;

  @ManyToOne
  @JoinColumn(name = "name")
  private CourseModel course;

}

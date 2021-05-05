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
@Table(name = "user")
@Getter @Setter @NoArgsConstructor
public class UserModel implements Serializable {

  @Id
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "full_name", nullable = false)
  private String full_name;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;

  @OneToMany(mappedBy = "user")
  private Set<CourseListModel> courseList = new HashSet<>();

}

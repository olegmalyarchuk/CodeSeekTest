package com.example.footballmanager.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "player")
@NoArgsConstructor
@AllArgsConstructor
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @Column(nullable = false)
  private int age;

  @Column(nullable = false)
  private int experienceMonths;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teamId", referencedColumnName = "id")
  private Team team;
}

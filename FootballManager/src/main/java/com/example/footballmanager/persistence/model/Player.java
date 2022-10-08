package com.example.footballmanager.persistence.model;

import io.swagger.annotations.ApiModelProperty;
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
  @ApiModelProperty(notes = "Player ID")
  private int id;

  @Column(nullable = false)
  @ApiModelProperty(notes = "Player Name")
  private String name;

  @Column(nullable = false)
  @ApiModelProperty(notes = "Player Surname")
  private String surname;

  @Column(nullable = false)
  @ApiModelProperty(notes = "Player age")
  private int age;

  @Column(name = "experience_months", nullable = false)
  @ApiModelProperty(notes = "Player months of experience")
  private int experienceMonths;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", referencedColumnName = "id")
  @ApiModelProperty(notes = "Player Team")
  private Team team;
}

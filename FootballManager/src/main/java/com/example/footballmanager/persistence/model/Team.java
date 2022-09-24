package com.example.footballmanager.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int wallet;

  @Column(nullable = false)
  private int commisionTeam;

  @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
  @Builder.Default
  private List<Player> players = new ArrayList<>();
}

package com.example.footballmanager.persistence.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
public class Team {
  @Id
  @ApiModelProperty(notes = "Team ID")
  private int id;

  @Column(nullable = false)
  @ApiModelProperty(notes = "Team Name")
  private String name;

  @Column(nullable = false)
  @ApiModelProperty(notes = "Team Wallet")
  private int wallet;

  @Column(name = "commision_team", nullable = false)
  @ApiModelProperty(notes = "Team commision")
  private int commisionTeam;
}

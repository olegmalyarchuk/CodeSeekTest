package com.example.footballmanager.dto;

import com.example.footballmanager.persistence.model.Player;
import com.example.footballmanager.validation.AdvancedInfo;
import com.example.footballmanager.validation.BasicInfo;
import com.example.footballmanager.validation.CommisionRange;
import com.example.footballmanager.validation.PositiveDigit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
  @PositiveDigit
  @NotNull(message = "{validation.id.not_null}")
  private int id;

  @Pattern(regexp = "^[A-ZА-Я][\\p{Alpha}А-Яа-я\\s-]{1,25}", groups = BasicInfo.class)
  @NotBlank(message = "{validation.name.not_null}", groups = AdvancedInfo.class)
  private String name;

  @PositiveDigit
  @NotNull(message = "{validation.id.not_null}")
  private int wallet;

  @PositiveDigit
  @CommisionRange
  @NotNull(message = "{validation.id.not_null}")
  private int commisionTeam;
}

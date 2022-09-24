package com.example.footballmanager.persistence.dao;

import com.example.footballmanager.persistence.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
  @Query(nativeQuery = true, value = "SELECT id as ROWCOUNT FROM team order by id desc limit 1")
  int calculateTeamsNumber();
}

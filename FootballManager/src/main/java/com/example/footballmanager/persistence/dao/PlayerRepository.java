package com.example.footballmanager.persistence.dao;

import com.example.footballmanager.persistence.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

  @Query(nativeQuery = true, value = "SELECT id as ROWCOUNT FROM player order by id desc limit 1")
  int calculatePlayersNumber();

  List<Player> findAllByTeamId(int teamId);
}

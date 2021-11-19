package com.match.service.repository;

import com.match.service.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Integer> {

    @Query("SELECT mo.id FROM MatchOdds mo where mo.matchId = :match_id")
    Integer findMatchOddsIdByMatchId(Integer match_id);
}

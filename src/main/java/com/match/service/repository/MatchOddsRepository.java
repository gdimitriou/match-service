package com.match.service.repository;

import com.match.service.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Integer> {

}

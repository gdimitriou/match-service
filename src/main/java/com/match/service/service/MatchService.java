package com.match.service.service;

import com.match.service.entity.Match;
import com.match.service.exception.ResourceNotFoundException;
import com.match.service.repository.MatchOddsRepository;
import com.match.service.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MatchOddsRepository matchOddsRepository;

    public List<Match> getAllMatches() {
        return this.matchRepository.findAll();
    }

    public ResponseEntity<Match> getMatchById(Integer id) throws ResourceNotFoundException {

        Match match = matchRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " +id));

         return ResponseEntity.ok().body(match);
    }

    public Match createMatch(Match match) {
        return this.matchRepository.save(match);
    }

    public ResponseEntity<Match> updateMatch(Integer id, Match matchRequest) throws ResourceNotFoundException {

        Match match = matchRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " +id));

        match.setMatchTime(matchRequest.getMatchTime());
        match.setMatchDate(matchRequest.getMatchDate());
        match.setTeamB(matchRequest.getTeamB());
        match.setTeamA(matchRequest.getTeamA());
        match.setDescription(match.getDescription());

        return ResponseEntity.ok(this.matchRepository.save(match));
    }

    public Map<String, Boolean> deleteMatchById(Integer id) throws ResourceNotFoundException {
        Match match = matchRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " +id));

        Map<String, Boolean> response =  new HashMap<>();
        response.put("Deleted" , Boolean.TRUE);
        return response;
    }
}

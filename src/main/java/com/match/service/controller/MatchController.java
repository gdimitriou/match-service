package com.match.service.controller;

import com.match.service.entity.Match;
import com.match.service.exception.ResourceNotFoundException;
import com.match.service.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping(value = "/match/getAll")
    public List<Match> getAllMatches(){
        return matchService.getAllMatches();
    }

    @GetMapping(value = "/match/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return this.matchService.getMatchById(id);
    }

    @PostMapping(value = "/match")
    public Match createMatch(@RequestBody Match match) throws ResourceNotFoundException {
        return this.matchService.createMatch(match);
    }

    @PutMapping(value = "/match/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable(value = "id") Integer id, @RequestBody Match matchRequest) throws ResourceNotFoundException {
        return this.matchService.updateMatch(id, matchRequest);
    }

    @DeleteMapping(value = "/match/{id}")
    public Map<String, Boolean> deleteMatch(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return this.matchService.deleteMatchById(id);
    }
}

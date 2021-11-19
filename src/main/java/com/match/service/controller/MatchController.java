package com.match.service.controller;

import com.match.service.entity.Match;
import com.match.service.exception.ResourceNotFoundException;
import com.match.service.model.dto.RequestModel;
import com.match.service.model.dto.ResponseModel;
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
    public List<ResponseModel> getAllMatches(){
        return matchService.getAllMatches();
    }

    @GetMapping(value = "/match/{id}")
    public ResponseEntity<ResponseModel> getMatchById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return this.matchService.getMatchById(id);
    }

    @PostMapping(value = "/match")
    public ResponseEntity<String> createMatch(@RequestBody RequestModel requestModel) throws ResourceNotFoundException {
        return this.matchService.createMatch(requestModel);
    }

    @PutMapping(value = "/match/{id}")
    public ResponseEntity<String> updateMatch(@PathVariable(value = "id") Integer id, @RequestBody RequestModel matchRequest) throws ResourceNotFoundException {
        return this.matchService.updateMatch(id, matchRequest);
    }

    @DeleteMapping(value = "/match/{id}")
    public Map<String, Boolean> deleteMatch(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return this.matchService.deleteMatchById(id);
    }
}

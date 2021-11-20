package com.match.service.service;

import com.match.service.entity.Match;
import com.match.service.entity.MatchOdds;
import com.match.service.exception.ResourceNotFoundException;
import com.match.service.model.dto.RequestModel;
import com.match.service.model.dto.ResponseModel;
import com.match.service.repository.MatchOddsRepository;
import com.match.service.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MatchOddsRepository matchOddsRepository;

    public List<ResponseModel> getAllMatches() {

        List<com.match.service.entity.Match> matchList = this.matchRepository.findAll();
        List<com.match.service.entity.MatchOdds> matchOddsList = this.matchOddsRepository.findAll();
        List<ResponseModel> responseModelList = new ArrayList<>();

        for(int i=0; i<matchList.size(); i++) {
            if(matchList.get(i).getId() == matchOddsList.get(i).getMatchId()) {
                ResponseModel responseModel = new ResponseModel();
                responseModel.setMatchId(matchList.get(i).getId());
                responseModel.setMatchDate(matchList.get(i).getMatchDate());
                responseModel.setMatchTime(matchList.get(i).getMatchTime());
                responseModel.setDescription(matchList.get(i).getDescription());
                responseModel.setTeamA(matchList.get(i).getTeamA());
                responseModel.setTeamB(matchList.get(i).getTeamB());
                responseModel.setSport(matchList.get(i).getSport());
                responseModel.setSpecifier(matchOddsList.get(i).getSpecifier());
                responseModel.setOdd(matchOddsList.get(i).getOdd());
                responseModelList.add(responseModel);
            }
        }

        return responseModelList;
    }

    public ResponseEntity<ResponseModel> getMatchById(Integer id) throws ResourceNotFoundException {

        Match match = matchRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " +id));

        Integer matchOddsId = matchOddsRepository.findMatchOddsIdByMatchId(id);

        MatchOdds matchOdds = matchOddsRepository
                .findById(matchOddsId)
                .orElseThrow(() -> new ResourceNotFoundException("MatchOdds not found with id: " +id));

        ResponseModel responseModel = new ResponseModel();
        responseModel.setMatchId(match.getId());
        responseModel.setMatchDate(match.getMatchDate());
        responseModel.setMatchTime(match.getMatchTime());
        responseModel.setDescription(match.getDescription());
        responseModel.setTeamA(match.getTeamA());
        responseModel.setTeamB(match.getTeamB());
        responseModel.setSport(match.getSport());
        responseModel.setSpecifier(matchOdds.getSpecifier());
        responseModel.setOdd(matchOdds.getOdd());

        return ResponseEntity.ok().body(responseModel);
    }

    @Transactional
    public ResponseEntity<String> createMatch(RequestModel requestModel) {

        com.match.service.entity.Match match = new Match();
        match.setMatchDate(requestModel.getMatchDate());
        match.setMatchTime(requestModel.getMatchTime());
        match.setTeamB(requestModel.getTeamB());
        match.setTeamA(requestModel.getTeamA());
        match.setDescription(requestModel.getDescription());
        match.setSport(requestModel.getSport());

        this.matchRepository.save(match);

        com.match.service.entity.MatchOdds matchOdds = new MatchOdds();
        matchOdds.setMatchId(match.getId());
        matchOdds.setOdd(requestModel.getOdd());
        matchOdds.setSpecifier(requestModel.getSpecifier());

        this.matchOddsRepository.save(matchOdds);

        return ResponseEntity.ok("Saved");
    }

    @Transactional
    public ResponseEntity<String> updateMatch(Integer id, RequestModel matchRequestForUpdate) throws ResourceNotFoundException {

        Match match = matchRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " +id));

        Integer matchOddsId = matchOddsRepository.findMatchOddsIdByMatchId(id);

        MatchOdds matchOdds = matchOddsRepository
                .findById(matchOddsId)
                .orElseThrow(() -> new ResourceNotFoundException("MatchOdds not found with id: " +id));

        match.setMatchTime(matchRequestForUpdate.getMatchTime());
        match.setMatchDate(matchRequestForUpdate.getMatchDate());
        match.setTeamB(matchRequestForUpdate.getTeamB());
        match.setTeamA(matchRequestForUpdate.getTeamA());
        match.setDescription(matchRequestForUpdate.getDescription());
        this.matchRepository.save(match);

        matchOdds.setSpecifier(matchRequestForUpdate.getSpecifier());
        matchOdds.setOdd(matchRequestForUpdate.getOdd());
        this.matchOddsRepository.save(matchOdds);

        return ResponseEntity.ok("Updated");
    }

    @Transactional
    public Map<String, Boolean> deleteMatchById(Integer id) throws ResourceNotFoundException {

        Match match = matchRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " +id));

        Integer matchOddsId = matchOddsRepository.findMatchOddsIdByMatchId(id);

        MatchOdds matchOdds = matchOddsRepository
                .findById(matchOddsId)
                .orElseThrow(() -> new ResourceNotFoundException("MatchOdds not found with id: " +id));

        this.matchRepository.deleteById(id);
        this.matchOddsRepository.deleteById(matchOddsId);

        Map<String, Boolean> response =  new HashMap<>();
        response.put("Deleted" , Boolean.TRUE);
        return response;
    }
}

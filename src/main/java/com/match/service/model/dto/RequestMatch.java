package com.match.service.model.dto;

import com.match.service.model.Match;
import com.match.service.model.MatchOdds;

public class RequestMatch {

    private Match match;
    private MatchOdds matchOdds;

    public RequestMatch() {
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public MatchOdds getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(MatchOdds matchOdds) {
        this.matchOdds = matchOdds;
    }
}

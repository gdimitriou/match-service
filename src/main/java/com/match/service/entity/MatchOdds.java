package com.match.service.entity;

import javax.persistence.*;

@Entity
@Table(name = "matchOdds")
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "match_id")
    private Integer matchId;

    @Column(name = "specifier")
    private String specifier;

    @Column(name = "odd")
    private Double odd;

    public MatchOdds() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public Double getOdd() {
        return odd;
    }

    public void setOdd(Double odd) {
        this.odd = odd;
    }
}

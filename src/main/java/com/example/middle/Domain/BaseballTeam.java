package com.example.middle.domain;

import java.time.LocalDate;

/**
 * 野球チームを表すドメイン.
 *
 * 球団情報を保持する.
 *
 * @author user
 *
 */
public class BaseballTeam {

    /** ID. */
    private Integer id;

    /** 球団名. */
    private String teamName;

    /** 本拠地. */
    private String homePlace;

    /** 発足日. */
    private LocalDate foundingDate;

    /** 歴史. */
    private String history;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(String homePlace) {
        this.homePlace = homePlace;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}

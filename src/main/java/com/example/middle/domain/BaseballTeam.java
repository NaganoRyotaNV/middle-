package com.example.middle.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 野球チームを表すドメイン.
 *
 * 球団情報を保持する.
 *
 * @author user
 *
 */
@Getter
@Setter
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

}

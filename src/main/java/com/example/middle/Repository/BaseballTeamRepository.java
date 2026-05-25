package com.example.middle.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.middle.domain.BaseballTeam;

/**
 * 野球チームを操作するリポジトリ.
 *
 * DBとのやり取りを行う.
 *
 * @author user
 *
 */
@Repository
public class BaseballTeamRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** ROW_MAPPER. */
    private static final RowMapper<BaseballTeam> BASEBALL_TEAM_ROW_MAPPER =
            new BeanPropertyRowMapper<>(BaseballTeam.class);

    /**
     * 発足日順で全件検索を行う.
     *
     * @return 球団一覧
     */
    public List<BaseballTeam> findAllOrderByFoundingDate() {

        String sql = """
                SELECT
                    id,
                    team_name,
                    home_place,
                    founding_date,
                    history
                FROM
                    baseball_teams
                ORDER BY
                    founding_date
                """;

        return template.query(sql, BASEBALL_TEAM_ROW_MAPPER);
    }

    /**
     * IDで球団情報を取得する.
     *
     * @param id ID
     *
     * @return 球団詳細
     */
    public BaseballTeam findById(Integer id) {

        String sql = """
                SELECT
                    id,
                    team_name,
                    home_place,
                    founding_date,
                    history
                FROM
                    baseball_teams
                WHERE
                    id = :id
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("id", id);

        return template.queryForObject(
                sql,
                param,
                BASEBALL_TEAM_ROW_MAPPER);
    }
}

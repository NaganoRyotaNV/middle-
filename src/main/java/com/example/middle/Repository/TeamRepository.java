package com.example.middle.Repository;


import com.example.middle.Domain.TeamDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class TeamRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<TeamDomain> TEAM_DOMAIN_ROW_MAPPER = new BeanPropertyRowMapper<>(TeamDomain.class);


    public List<TeamDomain> AllDate(){
        String sql = """
                SELECT 
                team_name,
                home_place,
                founding_date,
                history
                FROM
                baseball_teams
                ORDER BY founding_date
                """;


        List<TeamDomain> result = template.query(sql,TEAM_DOMAIN_ROW_MAPPER);

        return result;
    }

    public TeamDomain findByID(Integer id){
        String sql = """
                SELECT
                team_name,
                home_place,
                founding_date,
                history
                FROM,
                baseball_teams,
                WHERE 
                id = :id
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);

        return template.queryForObject(sql,param,TEAM_DOMAIN_ROW_MAPPER);
    }

}

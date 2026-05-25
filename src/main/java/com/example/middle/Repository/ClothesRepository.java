package com.example.middle.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.middle.domain.Clothes;

/**
 * 衣類情報を操作するリポジトリ.
 *
 * DBとのやり取りを行う。
 */
@Repository
public class ClothesRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** ROW_MAPPER. */
    private static final RowMapper<Clothes> CLOTHES_ROW_MAPPER =
            new BeanPropertyRowMapper<>(Clothes.class);

    /**
     * 性別と色で衣類情報を検索する.
     *
     * @param gender 性別
     * @param color 色
     * @return 衣類一覧
     */
    public List<Clothes> searchByColorAndGender(
            String gender,
            String color) {

        String sql = """
                SELECT
                    id,
                    genre,
                    gender,
                    color,
                    size,
                    price
                FROM
                    clothes
                WHERE
                    gender = :gender
                AND
                    color = :color
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("gender", gender);
        param.addValue("color", color);

        return template.query(sql, param, CLOTHES_ROW_MAPPER);
    }
}

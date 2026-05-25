package com.example.middle.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.middle.domain.Hotel;

/**
 * ホテル情報を操作するリポジトリ.
 *
 * DBとのやり取りを行う。
 */
@Repository
public class HotelRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** ROW_MAPPER. */
    private static final RowMapper<Hotel> HOTEL_ROW_MAPPER =
            new BeanPropertyRowMapper<>(Hotel.class);

    /**
     * 全件検索を行う.
     *
     * @return ホテル一覧
     */
    public List<Hotel> findAll() {
        String sql = """
                SELECT
                    id,
                    hotel_name,
                    nearest_station,
                    price
                FROM
                    hotels
                ORDER BY
                    price
                """;

        return template.query(sql, HOTEL_ROW_MAPPER);
    }

    /**
     * 指定価格以下のホテルを検索する.
     *
     * @param price 価格
     * @return ホテル一覧
     */
    public List<Hotel> searchByLessThanPrice(Integer price) {
        String sql = """
                SELECT
                    id,
                    hotel_name,
                    nearest_station,
                    price
                FROM
                    hotels
                WHERE
                    price <= :price
                ORDER BY
                    price
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("price", price);

        return template.query(sql, param, HOTEL_ROW_MAPPER);
    }
}

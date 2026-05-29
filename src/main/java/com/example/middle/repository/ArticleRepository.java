package com.example.middle.repository;

import com.example.middle.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 記事情報を操作するリポジトリ.
 */
@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER =
            new BeanPropertyRowMapper<>(Article.class);

    /**
     * 記事を全件取得する.
     *
     * @return 記事一覧
     */
    public List<Article> findAll() {
        String sql = """
                SELECT
                    id,
                    name,
                    content
                FROM
                    articles
                ORDER BY
                    id DESC
                """;

        return template.query(sql, ARTICLE_ROW_MAPPER);
    }

    /**
     * 記事を登録する.
     *
     * @param article 記事
     */
    public void insert(Article article) {
        String sql = """
                INSERT INTO articles (
                    name,
                    content
                ) VALUES (
                    :name,
                    :content
                )
                """;

        template.update(sql, new BeanPropertySqlParameterSource(article));
    }

    /**
     * 記事を削除する.
     *
     * @param id ID
     */
    public void deleteById(Integer id) {
        String sql = """
                DELETE FROM
                    articles
                WHERE
                    id = :id
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);

        template.update(sql, param);
    }
}

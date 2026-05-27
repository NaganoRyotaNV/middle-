package com.example.middle.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.middle.domain.Comment;

/**
 * コメント情報を操作するリポジトリ.
 */
@Repository
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** ROW_MAPPER. */
    private static final RowMapper<Comment> COMMENT_ROW_MAPPER =
            new BeanPropertyRowMapper<>(Comment.class);

    /**
     * 記事IDでコメントを取得する.
     *
     * @param articleId 記事ID
     * @return コメント一覧
     */
    public List<Comment> findByArticleId(Integer articleId) {
        String sql = """
                SELECT
                    id,
                    name,
                    content,
                    article_id
                FROM
                    comments
                WHERE
                    article_id = :articleId
                ORDER BY
                    id DESC
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("articleId", articleId);

        return template.query(sql, param, COMMENT_ROW_MAPPER);
    }

    /**
     * コメントを登録する.
     *
     * @param comment コメント
     */
    public void insert(Comment comment) {
        String sql = """
                INSERT INTO comments (
                    name,
                    content,
                    article_id
                ) VALUES (
                    :name,
                    :content,
                    :articleId
                )
                """;

        template.update(sql, new BeanPropertySqlParameterSource(comment));
    }

    /**
     * 記事IDに紐づくコメントを削除する.
     *
     * @param articleId 記事ID
     */
    public void deleteByArticleId(Integer articleId) {
        String sql = """
                DELETE FROM
                    comments
                WHERE
                    article_id = :articleId
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("articleId", articleId);

        template.update(sql, param);
    }
}

package com.example.middle.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.middle.domain.Article;
import com.example.middle.domain.Comment;

/**
 * 記事情報を操作するリポジトリ.
 */
@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 記事とコメントを結合して全件取得する.
     *
     * @return 記事一覧
     */
    public List<Article> findAllWithComments() {
        String sql = """
                SELECT
                    a.id AS article_id,
                    a.name AS article_name,
                    a.content AS article_content,
                    c.id AS comment_id,
                    c.name AS comment_name,
                    c.content AS comment_content,
                    c.article_id AS comment_article_id
                FROM
                    articles AS a
                LEFT JOIN
                    comments AS c
                ON
                    a.id = c.article_id
                ORDER BY
                    a.id DESC,
                    c.id DESC
                """;

        return template.query(sql, new ArticleWithCommentsExtractor());
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

    private static class ArticleWithCommentsExtractor
            implements ResultSetExtractor<List<Article>> {

        @Override
        public List<Article> extractData(ResultSet rs) throws SQLException {
            Map<Integer, Article> articleMap = new LinkedHashMap<>();

            while (rs.next()) {
                Integer articleId = rs.getInt("article_id");

                Article article = articleMap.computeIfAbsent(
                        articleId,
                        id -> createArticle(rs));

                Integer commentId = (Integer) rs.getObject("comment_id");
                if (commentId != null) {
                    article.getCommentList().add(createComment(rs));
                }
            }

            return new ArrayList<>(articleMap.values());
        }

        private Article createArticle(ResultSet rs) {
            try {
                Article article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setName(rs.getString("article_name"));
                article.setContent(rs.getString("article_content"));
                return article;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

        private Comment createComment(ResultSet rs) throws SQLException {
            Comment comment = new Comment();
            comment.setId(rs.getInt("comment_id"));
            comment.setName(rs.getString("comment_name"));
            comment.setContent(rs.getString("comment_content"));
            comment.setArticleId(rs.getInt("comment_article_id"));
            return comment;
        }
    }
}

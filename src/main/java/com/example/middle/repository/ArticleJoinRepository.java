package com.example.middle.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.middle.domain.Article;
import com.example.middle.domain.Comment;

/**
 * 記事とコメントを結合して取得するリポジトリ.
 */
@Repository
public class ArticleJoinRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 記事とコメントを1回のSQLで取得する.
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

    private static class ArticleWithCommentsExtractor
            implements ResultSetExtractor<List<Article>> {

        @Override
        public List<Article> extractData(ResultSet rs) throws SQLException {
            Map<Integer, Article> articleMap = new LinkedHashMap<>();

            while (rs.next()) {
                Integer articleId = rs.getInt("article_id");
                Article article = articleMap.get(articleId);

                if (article == null) {
                    article = createArticle(rs);
                    articleMap.put(articleId, article);
                }

                Integer commentId = (Integer) rs.getObject("comment_id");
                if (commentId != null) {
                    article.getCommentList().add(createComment(rs));
                }
            }

            return new ArrayList<>(articleMap.values());
        }

        private Article createArticle(ResultSet rs) throws SQLException {
            Article article = new Article();
            article.setId(rs.getInt("article_id"));
            article.setName(rs.getString("article_name"));
            article.setContent(rs.getString("article_content"));
            return article;
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

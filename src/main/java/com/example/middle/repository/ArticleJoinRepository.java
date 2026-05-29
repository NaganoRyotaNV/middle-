package com.example.middle.repository;

import com.example.middle.domain.Article;
import com.example.middle.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
                    a.id AS a_id, a.name AS a_name, a.content AS a_content,
                    c.id AS c_id, c.name AS c_name, c.content AS c_content
                FROM articles a
                LEFT JOIN comments c ON a.id = c.article_id
                ORDER BY a.id, c.id
                """;

        return template.query(sql, rs -> {
            List<Article> articleList = new ArrayList<>();
            Article currentArticle = null;

            while (rs.next()) {
                int articleId = rs.getInt("a_id");

                if (currentArticle == null || currentArticle.getId() != articleId) {
                    currentArticle = new Article();
                    currentArticle.setId(articleId);
                    currentArticle.setName(rs.getString("a_name"));
                    currentArticle.setContent(rs.getString("a_content"));
                    currentArticle.setCommentList(new ArrayList<>());

                    articleList.add(currentArticle);
                }

                int commentId = rs.getInt("c_id");
                if (commentId != 0) {
                    Comment c = new Comment();
                    c.setId(commentId);
                    c.setName(rs.getString("c_name"));
                    c.setContent(rs.getString("c_content"));
                    c.setArticleId(articleId);

                    currentArticle.getCommentList().add(c);
                }
            }
            return articleList;
        });
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



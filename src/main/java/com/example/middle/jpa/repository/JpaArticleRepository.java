package com.example.middle.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.middle.jpa.entity.JpaArticle;

/**
 * JPAで記事情報を操作するリポジトリ.
 */
@Repository
public interface JpaArticleRepository
        extends JpaRepository<JpaArticle, Integer> {

    /**
     * 記事をID降順で全件取得する.
     *
     * @return 記事一覧
     */
    List<JpaArticle> findAllByOrderByIdDesc();

    /**
     * 1回のSQLで記事とコメントを削除する.
     *
     * @param articleId 記事ID
     * @return 更新件数
     */
    @Modifying
    @Transactional
    @Query(value = """
            WITH deleted_comments AS (
                DELETE FROM comments
                WHERE article_id = :articleId
                RETURNING id
            )
            DELETE FROM articles
            WHERE id = :articleId
            """, nativeQuery = true)
    int deleteArticleAndCommentsById(
            @Param("articleId") Integer articleId);
}

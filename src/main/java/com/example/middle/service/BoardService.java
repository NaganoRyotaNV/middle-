package com.example.middle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.middle.domain.Article;
import com.example.middle.domain.Comment;
import com.example.middle.repository.ArticleRepository;
import com.example.middle.repository.CommentRepository;

/**
 * 掲示板を操作するサービス.
 */
@Service
public class BoardService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 記事とコメントを取得する.
     *
     * @return 記事一覧
     */
    public List<Article> showList() {
        return articleRepository.findAllWithComments();
    }

    /**
     * 記事を投稿する.
     *
     * @param article 記事
     */
    public void postArticle(Article article) {
        articleRepository.insert(article);
    }

    /**
     * コメントを投稿する.
     *
     * @param comment コメント
     */
    public void postComment(Comment comment) {
        commentRepository.insert(comment);
    }

    /**
     * 記事と紐づくコメントを削除する.
     *
     * @param articleId 記事ID
     */
    @Transactional
    public void deleteArticle(Integer articleId) {
        commentRepository.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }
}

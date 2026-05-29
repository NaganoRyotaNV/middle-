package com.example.middle.controller;

import com.example.middle.domain.Article;
import com.example.middle.domain.Comment;
import com.example.middle.form.ArticleForm;
import com.example.middle.form.CommentForm;
import com.example.middle.repository.ArticleRepository;
import com.example.middle.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 掲示板を操作するコントローラ.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 掲示板を表示する.
     *
     * @param model モデル
     * @return 掲示板画面
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("articleList", findArticleList());
        return "board/index";
    }

    /**
     * 記事を投稿する.
     *
     * @param form 記事フォーム
     * @return 掲示板画面
     */
    @PostMapping("/articles")
    public String insertArticle(@ModelAttribute ArticleForm form) {
        Article article = new Article();
        BeanUtils.copyProperties(form, article);
        articleRepository.insert(article);
        return "redirect:/board";
    }

    /**
     * コメントを投稿する.
     *
     * @param form コメントフォーム
     * @return 掲示板画面
     */
    @PostMapping("/comments")
    public String insertComment(@ModelAttribute CommentForm form) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(form, comment);
        commentRepository.insert(comment);
        return "redirect:/board";
    }

    /**
     * 記事とコメントを削除する.
     *
     * @param articleId 記事ID
     * @return 掲示板画面
     */
    @PostMapping("/articles/delete")
    public String deleteArticle(@RequestParam Integer articleId) {
        commentRepository.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
        return "redirect:/board";
    }

    private List<Article> findArticleList() {
        java.util.List<Article> articleList = articleRepository.findAll();

        for (Article article : articleList) {
            article.setCommentList(
                    commentRepository.findByArticleId(article.getId()));
        }

        return articleList;
    }
}

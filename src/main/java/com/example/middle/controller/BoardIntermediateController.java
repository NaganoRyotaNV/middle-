package com.example.middle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.middle.domain.Article;
import com.example.middle.domain.Comment;
import com.example.middle.form.ArticleForm;
import com.example.middle.form.CommentForm;
import com.example.middle.repository.ArticleJoinRepository;
import com.example.middle.repository.ArticleRepository;
import com.example.middle.repository.CommentRepository;

/**
 * 中級課題用の掲示板コントローラ.
 */
@Controller
@RequestMapping("/board/intermediate")
public class BoardIntermediateController {

    @Autowired
    private ArticleJoinRepository articleJoinRepository;

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
        model.addAttribute(
                "articleList",
                articleJoinRepository.findAllWithComments());
        return "board/intermediate";
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
        return "redirect:/board/intermediate";
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
        return "redirect:/board/intermediate";
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
        return "redirect:/board/intermediate";
    }
}

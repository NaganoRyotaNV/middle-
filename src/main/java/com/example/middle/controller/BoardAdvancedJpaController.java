package com.example.middle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.middle.form.AdvancedArticleForm;
import com.example.middle.form.AdvancedCommentForm;
import com.example.middle.jpa.entity.JpaArticle;
import com.example.middle.jpa.entity.JpaComment;
import com.example.middle.jpa.repository.JpaArticleRepository;
import com.example.middle.jpa.repository.JpaCommentRepository;

/**
 * 上級課題用のJPA掲示板コントローラ.
 */
@Controller
@RequestMapping("/board/advanced-jpa")
public class BoardAdvancedJpaController {

    @Autowired
    private JpaArticleRepository articleRepository;

    @Autowired
    private JpaCommentRepository commentRepository;

    /**
     * 掲示板を表示する.
     *
     * @param model モデル
     * @return 掲示板画面
     */
    @GetMapping
    public String index(Model model) {
        setDefaultAttributes(model);
        return "board/advanced-jpa";
    }

    /**
     * 記事を投稿する.
     *
     * @param form 記事フォーム
     * @param result エラー情報
     * @param model モデル
     * @return 掲示板画面
     */
    @PostMapping("/articles")
    public String insertArticle(
            @Validated @ModelAttribute("advancedArticleForm")
                    AdvancedArticleForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute(
                    "advancedCommentForm",
                    new AdvancedCommentForm());
            model.addAttribute(
                    "articleList",
                    articleRepository.findAllByOrderByIdDesc());
            return "board/advanced-jpa";
        }

        JpaArticle article = new JpaArticle();
        BeanUtils.copyProperties(form, article);
        articleRepository.save(article);

        return "redirect:/board/advanced-jpa";
    }

    /**
     * コメントを投稿する.
     *
     * @param form コメントフォーム
     * @param result エラー情報
     * @param model モデル
     * @return 掲示板画面
     */
    @PostMapping("/comments")
    public String insertComment(
            @Validated @ModelAttribute("advancedCommentForm")
                    AdvancedCommentForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute(
                    "advancedArticleForm",
                    new AdvancedArticleForm());
            model.addAttribute("commentErrorArticleId", form.getArticleId());
            model.addAttribute(
                    "articleList",
                    articleRepository.findAllByOrderByIdDesc());
            return "board/advanced-jpa";
        }

        JpaComment comment = new JpaComment();
        BeanUtils.copyProperties(form, comment);
        commentRepository.save(comment);

        return "redirect:/board/advanced-jpa";
    }

    /**
     * 1回のSQLで記事とコメントを削除する.
     *
     * @param articleId 記事ID
     * @return 掲示板画面
     */
    @PostMapping("/articles/delete")
    public String deleteArticle(@RequestParam Integer articleId) {
        articleRepository.deleteArticleAndCommentsById(articleId);
        return "redirect:/board/advanced-jpa";
    }

    private void setDefaultAttributes(Model model) {
        model.addAttribute(
                "advancedArticleForm",
                new AdvancedArticleForm());
        model.addAttribute(
                "advancedCommentForm",
                new AdvancedCommentForm());
        model.addAttribute(
                "articleList",
                articleRepository.findAllByOrderByIdDesc());
    }
}

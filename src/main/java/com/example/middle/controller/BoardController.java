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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    /** 記事ごとのいいね数. */
    private final Map<Integer, Integer> likeCountMap = new ConcurrentHashMap<>();

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
        likeCountMap.remove(articleId);
        return "redirect:/board";
    }

    /**
     * 記事のいいね数を非同期で増やす.
     *
     * @param articleId 記事ID
     * @return いいね数
     */
    @PostMapping("/articles/like")
    @ResponseBody
    public Map<String, Integer> likeArticle(@RequestParam Integer articleId) {
        Integer likeCount = likeCountMap.merge(articleId, 1, Integer::sum);

        Map<String, Integer> result = new HashMap<>();
        result.put("likeCount", likeCount);
        return result;
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

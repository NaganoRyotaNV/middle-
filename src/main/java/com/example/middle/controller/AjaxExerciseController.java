package com.example.middle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * JavaScript / jQuery / Ajax 課題画面を表示するコントローラ.
 */
@Controller
@RequestMapping("/ajax")
public class AjaxExerciseController {

    /**
     * Ajax 課題1〜4の確認画面を表示する.
     *
     * @return Ajax 課題画面
     */
    @GetMapping
    public String index() {
        return "ajax/index";
    }
}

package com.example.middle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.middle.domain.Clothes;
import com.example.middle.service.ClothesService;

/**
 * 衣類情報を操作するコントローラ.
 *
 * 画面遷移を行う。
 */
@Controller
@RequestMapping("/clothes")
public class ClothesController {

    @Autowired
    private ClothesService clothesService;

    /**
     * 衣類検索画面を表示する.
     *
     * @return 衣類検索画面
     */
    @GetMapping
    public String index() {
        return "clothes/index";
    }

    /**
     * 衣類検索を行う.
     *
     * @param gender 性別
     * @param color 色
     * @param model モデル
     * @return 衣類検索画面
     */
    @GetMapping("/search")
    public String search(
            @RequestParam String gender,
            @RequestParam String color,
            Model model) {

        List<Clothes> clothesList =
                clothesService.searchByColorAndGender(
                        gender,
                        color);

        if (clothesList.isEmpty()) {
            model.addAttribute(
                    "message",
                    "該当の衣類情報は存在しません");
        }

        model.addAttribute("clothesList", clothesList);
        model.addAttribute("gender", gender);
        model.addAttribute("color", color);

        return "clothes/index";
    }
}

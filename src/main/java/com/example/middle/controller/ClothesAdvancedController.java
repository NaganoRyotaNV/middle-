package com.example.middle.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.middle.domain.Clothes;
import com.example.middle.domain.ClothesColor;
import com.example.middle.domain.Gender;
import com.example.middle.service.ClothesService;

/**
 * 選択肢をMapとEnumで扱う衣類検索コントローラ.
 */
@Controller
@RequestMapping("/clothes/advanced")
public class ClothesAdvancedController {

    @Autowired
    private ClothesService clothesService;

    /**
     * 衣類検索画面を表示する.
     *
     * @param model モデル
     * @return 衣類検索画面
     */
    @GetMapping
    public String index(Model model) {
        addOptions(model);
        model.addAttribute("gender", Gender.MAN.getValue());
        model.addAttribute("color", ClothesColor.RED.getValue());
        return "clothes/advanced";
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

        addOptions(model);
        model.addAttribute("clothesList", clothesList);
        model.addAttribute("gender", gender);
        model.addAttribute("color", color);

        return "clothes/advanced";
    }

    private void addOptions(Model model) {
        Map<String, String> genderMap =
                Arrays.stream(Gender.values())
                        .collect(Collectors.toMap(
                                Gender::getValue,
                                Gender::getLabel));

        Map<String, String> colorMap =
                Arrays.stream(ClothesColor.values())
                        .collect(Collectors.toMap(
                                ClothesColor::getValue,
                                ClothesColor::getLabel));

        model.addAttribute("genderMap", genderMap);
        model.addAttribute("colorMap", colorMap);
    }
}

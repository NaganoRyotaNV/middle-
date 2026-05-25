package com.example.middle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.middle.domain.Hotel;
import com.example.middle.service.HotelService;

/**
 * ホテル情報を操作するコントローラ.
 *
 * 画面遷移を行う。
 */
@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * ホテル検索画面を表示する.
     *
     * @return ホテル検索画面
     */
    @GetMapping
    public String index() {
        return "hotel/index";
    }

    /**
     * ホテル検索を行う.
     *
     * @param price 価格
     * @param model モデル
     * @return ホテル検索画面
     */
    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) Integer price,
            Model model) {

        List<Hotel> hotelList =
                hotelService.searchByLessThanPrice(price);

        if (hotelList.isEmpty()) {
            model.addAttribute(
                    "message",
                    "該当のホテル情報は存在しません");
        }

        model.addAttribute("hotelList", hotelList);
        model.addAttribute("price", price);

        return "hotel/index";
    }
}

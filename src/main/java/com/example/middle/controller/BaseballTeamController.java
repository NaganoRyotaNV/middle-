package com.example.middle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.middle.domain.BaseballTeam;
import com.example.middle.service.BaseballTeamService;

/**
 * 野球チームを操作するコントローラ.
 *
 * 画面遷移を行う.
 * @author user
 */
@Controller
public class BaseballTeamController {

    @Autowired
    private BaseballTeamService baseballTeamService;

    /**
     * 球団一覧画面を表示する.
     *
     * @param model モデル
     * @return 一覧画面
     */
    @GetMapping("/teams")
    public String index(Model model) {

        model.addAttribute(
                "teamList",
                baseballTeamService.showList());

        return "team/list";
    }

    /**
     * 球団詳細画面を表示する.
     *
     * @param id ID
     * @param model モデル
     * @return 詳細画面
     */
    @GetMapping("/teams/{id}")
    public String detail(
            @PathVariable Integer id,
            Model model) {

        BaseballTeam team =
                baseballTeamService.showDetail(id);

        model.addAttribute("team", team);

        return "team/detail";
    }
}

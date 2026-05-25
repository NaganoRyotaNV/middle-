package com.example.middle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.middle.domain.BaseballTeam;
import com.example.middle.repository.BaseballTeamRepository;

/**
 * 野球チームを操作するサービス.
 *
 * 業務処理を行う.
 * @author user
 */
@Service
public class BaseballTeamService {

    @Autowired
    private BaseballTeamRepository baseballTeamRepository;

    /**
     * 球団一覧を取得する.
     *
     * @return 球団一覧
     */
    public List<BaseballTeam> showList() {
        return baseballTeamRepository.findAllOrderByFoundingDate();
    }

    /**
     * 球団詳細を取得する.
     *
     * @param id ID
     * @return 球団詳細
     */
    public BaseballTeam showDetail(Integer id) {
        return baseballTeamRepository.findById(id);
    }
}

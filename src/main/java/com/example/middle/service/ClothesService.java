package com.example.middle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.middle.domain.Clothes;
import com.example.middle.repository.ClothesRepository;

/**
 * 衣類情報を操作するサービス.
 *
 * 業務処理を行う。
 */
@Service
public class ClothesService {

    @Autowired
    private ClothesRepository clothesRepository;

    /**
     * 性別と色で衣類情報を検索する.
     *
     * @param gender 性別
     * @param color 色
     * @return 衣類一覧
     */
    public List<Clothes> searchByColorAndGender(
            String gender,
            String color) {

        return clothesRepository.searchByColorAndGender(
                gender,
                color);
    }
}

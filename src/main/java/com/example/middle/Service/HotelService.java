package com.example.middle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.middle.domain.Hotel;
import com.example.middle.repository.HotelRepository;

/**
 * ホテル情報を操作するサービス.
 *
 * 業務処理を行う。
 */
@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    /**
     * ホテル検索を行う.
     *
     * @param price 価格
     * @return ホテル一覧
     */
    public List<Hotel> searchByLessThanPrice(Integer price) {
        if (price == null) {
            return hotelRepository.findAll();
        }

        return hotelRepository.searchByLessThanPrice(price);
    }
}

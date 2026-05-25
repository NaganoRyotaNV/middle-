package com.example.middle.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * ホテル情報を表すドメイン.
 *
 * ホテル検索結果を保持する。
 */

@Getter
@Setter

public class Hotel {

    /** ID. */
    private Integer id;

    /** ホテル名. */
    private String hotelName;

    /** 最寄駅. */
    private String nearestStation;

    /** 価格. */
    private Integer price;

}

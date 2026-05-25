package com.example.middle.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 衣類情報を表すドメイン.
 *
 * 衣類検索結果を保持する。
 */

@Getter
@Setter
public class Clothes {

    /** ID. */
    private Integer id;

    /** ジャンル. */
    private String genre;

    /** 性別. */
    private String gender;

    /** 色. */
    private String color;

    /** サイズ. */
    private String size;

    /** 価格. */
    private Integer price;
}

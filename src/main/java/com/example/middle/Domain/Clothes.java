package com.example.middle.domain;

/**
 * 衣類情報を表すドメイン.
 *
 * 衣類検索結果を保持する。
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

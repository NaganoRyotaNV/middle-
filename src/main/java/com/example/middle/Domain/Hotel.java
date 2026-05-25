package com.example.middle.domain;

/**
 * ホテル情報を表すドメイン.
 *
 * ホテル検索結果を保持する。
 */
public class Hotel {

    /** ID. */
    private Integer id;

    /** ホテル名. */
    private String hotelName;

    /** 最寄駅. */
    private String nearestStation;

    /** 価格. */
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

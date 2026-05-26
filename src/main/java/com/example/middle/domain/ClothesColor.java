package com.example.middle.domain;

/**
 * 衣類検索で選択できる色.
 */
public enum ClothesColor {

    RED("赤", "赤"),
    BLUE("青", "青"),
    WHITE("白", "白"),
    YELLOW("黄", "黄");

    /** リクエスト値. */
    private final String value;

    /** 表示名. */
    private final String label;

    ClothesColor(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}

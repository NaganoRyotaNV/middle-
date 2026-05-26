package com.example.middle.domain;

/**
 * 衣類検索で選択できる性別.
 */
public enum Gender {

    MAN("Man", "Man"),
    WOMAN("Woman", "Woman");

    /** リクエスト値. */
    private final String value;

    /** 表示名. */
    private final String label;

    Gender(String value, String label) {
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

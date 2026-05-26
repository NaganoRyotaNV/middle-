package com.example.middle.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 記事情報を表すドメイン.
 */
@Getter
@Setter
public class Article {

    /** ID. */
    private Integer id;

    /** 投稿者名. */
    private String name;

    /** 記事内容. */
    private String content;

    /** コメント一覧. */
    private List<Comment> commentList = new ArrayList<>();
}

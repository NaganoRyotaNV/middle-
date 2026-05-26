package com.example.middle.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 記事投稿フォーム.
 */
@Getter
@Setter
public class ArticleForm {

    /** 投稿者名. */
    private String name;

    /** 記事内容. */
    private String content;
}

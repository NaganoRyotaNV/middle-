package com.example.middle.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * コメント情報を表すドメイン.
 */
@Getter
@Setter
public class Comment {

    /** ID. */
    private Integer id;

    /** コメント者名. */
    private String name;

    /** コメント内容. */
    private String content;

    /** 記事ID. */
    private Integer articleId;
}

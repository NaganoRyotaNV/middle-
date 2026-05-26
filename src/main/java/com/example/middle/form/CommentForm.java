package com.example.middle.form;

import lombok.Getter;
import lombok.Setter;

/**
 * コメント投稿フォーム.
 */
@Getter
@Setter
public class CommentForm {

    /** 記事ID. */
    private Integer articleId;

    /** コメント者名. */
    private String name;

    /** コメント内容. */
    private String content;
}

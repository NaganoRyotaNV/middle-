package com.example.middle.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 上級課題用のコメント投稿フォーム.
 */
@Getter
@Setter
public class AdvancedCommentForm {

    /** 記事ID. */
    @NotNull
    private Integer articleId;

    /** コメント者名. */
    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50字以内で入力してください")
    private String name;

    /** コメント内容. */
    @NotBlank(message = "コメントを入力してください")
    private String content;
}

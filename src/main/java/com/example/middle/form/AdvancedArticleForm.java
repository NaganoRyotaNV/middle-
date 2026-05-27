package com.example.middle.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 上級課題用の記事投稿フォーム.
 */
@Getter
@Setter
public class AdvancedArticleForm {

    /** 投稿者名. */
    @NotBlank(message = "投稿者名を入力してください")
    @Size(max = 50, message = "投稿者名は50字以内で入力してください")
    private String name;

    /** 記事内容. */
    @NotBlank(message = "投稿内容を入力してください")
    private String content;
}

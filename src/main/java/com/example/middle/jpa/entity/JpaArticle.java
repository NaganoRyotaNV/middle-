package com.example.middle.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * JPAで扱う記事エンティティ.
 */
@Entity
@Table(name = "articles")
@Getter
@Setter
public class JpaArticle {

    /** ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 投稿者名. */
    private String name;

    /** 記事内容. */
    private String content;

    /** コメント一覧. */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "article_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    @OrderBy("id DESC")
    private List<JpaComment> commentList = new ArrayList<>();
}

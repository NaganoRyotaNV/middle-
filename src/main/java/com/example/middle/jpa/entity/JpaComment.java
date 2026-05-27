package com.example.middle.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * JPAで扱うコメントエンティティ.
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
public class JpaComment {

    /** ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** コメント者名. */
    private String name;

    /** コメント内容. */
    private String content;

    /** 記事ID. */
    @Column(name = "article_id")
    private Integer articleId;
}

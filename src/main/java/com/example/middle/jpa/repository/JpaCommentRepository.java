package com.example.middle.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middle.jpa.entity.JpaComment;

/**
 * JPAでコメント情報を操作するリポジトリ.
 */
@Repository
public interface JpaCommentRepository
        extends JpaRepository<JpaComment, Integer> {
}

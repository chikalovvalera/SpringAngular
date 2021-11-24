package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<ImageModel> findAllByPost(Post post);

    List<ImageModel> findAllByIdAndUserId(Long commentId, Long userId);
}

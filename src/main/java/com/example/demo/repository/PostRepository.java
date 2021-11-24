package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface PostRepository  extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOOrderByCreatedDateDest(Users user);

    List<Post> findAllByCreatedDateDest(LocalDateTime date);

    Optional<Post> findPostByIdAndUser(Long id, Users user);
}

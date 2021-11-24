package com.example.demo.repository;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Post;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByUserId(String username);

    Optional<ImageModel> findByPostId(Long id);
}

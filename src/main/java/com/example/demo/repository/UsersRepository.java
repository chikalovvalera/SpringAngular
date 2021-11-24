package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByUsername(String username);

    Optional<Users> findUsersByEmail(String email);

    Optional<Users> findUsersById(Long id);

}

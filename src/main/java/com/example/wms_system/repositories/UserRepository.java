package com.example.wms_system.repositories;

import com.example.wms_system.enums.Role;
import com.example.wms_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.role=?1")
    List<User> findAllByRole(Role role);
    @Query("SELECT u from User u WHERE u.username LIKE CONCAT('%', :username, '%')")
    List<User> findAllByUsernamePart(String username);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);

    @Query("update User u set u.enabled=false where u.uuid=:id and u.enabled=true")
    @Modifying
    @Override
    void deleteById(@Param("id") String id);
}
package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindUserByUsername_UserExists() {
        // Arrange
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("password123")
                .enabled(true)
                .build();
        userRepository.save(user);

        // Act
        Optional<User> foundUser = userRepository.findUserByUsername("johndoe");

        // Assert
        assertTrue(foundUser.isPresent());
        assertTrue(foundUser.get().getUsername().equals("johndoe"));
    }

    @Test
    void testFindUserByUsername_UserDoesNotExist() {
        // Act
        Optional<User> foundUser = userRepository.findUserByUsername("nonexistentuser");

        // Assert
        assertFalse(foundUser.isPresent());
    }
}
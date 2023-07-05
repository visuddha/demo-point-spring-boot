package com.visuddha.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.visuddha.api.models.Role;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void UserRepository_SaveUser_RetunedSavedUser() {

        // arrange
        Role role = Role.builder().name("USER").build();
        List<Role> roles = new ArrayList<>();
        roles.add(0, role);

        UserEntity user = UserEntity.builder()
                .username("demouserrepo01")
                .password("demo")
                .roles(roles)
                .build();

        // act
        UserEntity savedUser = userRepository.save(user);

        // assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetUser_GetUserByUserName() {

        // arrange
        Role role = Role.builder().name("USER").build();
        List<Role> roles = new ArrayList<>();
        roles.add(0, role);

        UserEntity user = UserEntity.builder()
                .username("demouserrepo02")
                .password("demo")
                .roles(roles)
                .build();

        UserEntity demoUser = userRepository.save(user);

        // act
        Optional<UserEntity> savedUser = userRepository.findByUsername(user.getUsername());

        // assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.get().getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.get().getUsername()).isEqualTo(demoUser.getUsername());
    }

    @Test
    public void UserRepository_ExistsByUsername_UserExistsByUsername() {

        // arrange
        Role role = Role.builder().name("USER").build();
        List<Role> roles = new ArrayList<>();
        roles.add(0, role);

        UserEntity user = UserEntity.builder()
                .username("demouserrepo03")
                .password("demo")
                .roles(roles)
                .build();

        UserEntity demoUser = userRepository.save(user);

        // act
        Boolean IsUserExsits = userRepository.existsByUsername(demoUser.getUsername());

        // assert
        Assertions.assertThat(IsUserExsits).isNotNull();
        Assertions.assertThat(IsUserExsits).isTrue();
    }

}
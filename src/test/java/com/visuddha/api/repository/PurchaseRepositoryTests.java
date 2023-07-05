package com.visuddha.api.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.Role;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PurchaseRepositoryTests {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    public void PurchaseRepository_SavePurchase_RetunedSavedPurchase() {

        // arrange
        Role role = Role.builder().name("USER").build();
        List<Role> roles = new ArrayList<>();
        roles.add(0, role);

        UserEntity user = UserEntity.builder().username("demotest001").password("demo").roles(roles).build();
        UserEntity demoUser = userRepository.save(user);
        Purchase purchase = Purchase.builder()
                .purchase_amount(200)
                .purchase_item("demotest00")
                .points(200)
                .purchase_date(new Date(222222))
                .user(demoUser)
                .build();

        // act
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // assert
        Assertions.assertThat(savedPurchase).isNotNull();
        Assertions.assertThat(savedPurchase.getId()).isGreaterThan(0);
    }

    @Test
    public void PurchaseRepository_GetPurchaseById_ReturnedSavedPurchase() {

        // arrange
        Role role = Role.builder().name("USER").build();
        List<Role> roles = new ArrayList<>();
        roles.add(0, role);

        UserEntity user = UserEntity.builder().username("demotest002").password("demo").roles(roles).build();
        UserEntity demoUser = userRepository.save(user);
        Purchase purchase = Purchase.builder()
                .purchase_amount(200)
                .purchase_item("demotest01")
                .points(200)
                .purchase_date(new Date(222222))
                .user(demoUser)
                .build();

        Purchase testPurchase = purchaseRepository.save(purchase);

        // act
        Purchase savedPurchase = purchaseRepository.getById(testPurchase.getId());

        // assert
        Assertions.assertThat(savedPurchase).isNotNull();
        Assertions.assertThat(savedPurchase.getId()).isGreaterThan(0);
        Assertions.assertThat(savedPurchase.getId()).isEqualTo(testPurchase.getId());
    }

    // @Test
    // public void PurchaseRepository_GetPointsByUserId_ReturnUserPoints() {

    // // arrange
    // Role role = Role.builder().name("USER").build();
    // List<Role> roles = new ArrayList<>();
    // roles.add(0, role);

    // UserEntity user =
    // UserEntity.builder().username("demotest003").password("demo").roles(roles).build();
    // UserEntity demoUser = userRepository.save(user);

    // Purchase purchase = Purchase.builder()
    // .purchase_amount(200f)
    // .purchase_item("demotest02")
    // .points(200)
    // .purchase_date(new Date(222222))
    // .user(demoUser)
    // .build();

    // Purchase demoPurchase = purchaseRepository.save(purchase);
    // // act
    // int userPoints = purchaseRepository.getPointsByUserId(demoUser.getId());

    // // assert
    // Assertions.assertThat(userPoints).isNotNull();
    // Assertions.assertThat(userPoints).isGreaterThan(0);
    // }

}

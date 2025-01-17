package com.mindhub.todolist;
import com.mindhub.todolist.models.AdminEntity;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.IAdminEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IAdminEntityRepositoryTest {

    @Autowired
    private IAdminEntityRepository adminEntityRepository;

    @Test
    void testSaveAdminEntity() {
        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        AdminEntity admin = new AdminEntity("John", "Doe", user);
        AdminEntity savedAdmin = adminEntityRepository.save(admin);

        assertThat(savedAdmin).isNotNull();
        assertThat(savedAdmin.getId()).isNotNull();
        assertThat(savedAdmin.getName()).isEqualTo("John");
        assertThat(savedAdmin.getLastname()).isEqualTo("Doe");
        assertThat(savedAdmin.getUser().getEmail()).isEqualTo("testuser@example.com");
    }

    @Test
    void testFindById() {
        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        AdminEntity admin = new AdminEntity("John", "Doe", user);
        AdminEntity savedAdmin = adminEntityRepository.save(admin);

        AdminEntity foundAdmin = adminEntityRepository.findById(savedAdmin.getId()).orElse(null);

        assertThat(foundAdmin).isNotNull();
        assertThat(foundAdmin.getId()).isEqualTo(savedAdmin.getId());
    }

    @Test
    void testUpdateAdminEntity() {
        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        AdminEntity admin = new AdminEntity("John", "Doe", user);
        AdminEntity savedAdmin = adminEntityRepository.save(admin);

        savedAdmin.setName("Jane");
        savedAdmin.setLastname("Smith");
        AdminEntity updatedAdmin = adminEntityRepository.save(savedAdmin);

        assertThat(updatedAdmin.getName()).isEqualTo("Jane");
        assertThat(updatedAdmin.getLastname()).isEqualTo("Smith");
    }

    @Test
    void testDeleteAdminEntity() {
        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        AdminEntity admin = new AdminEntity("John", "Doe", user);
        AdminEntity savedAdmin = adminEntityRepository.save(admin);

        adminEntityRepository.deleteById(savedAdmin.getId());
        AdminEntity deletedAdmin = adminEntityRepository.findById(savedAdmin.getId()).orElse(null);

        assertThat(deletedAdmin).isNull();
    }
}

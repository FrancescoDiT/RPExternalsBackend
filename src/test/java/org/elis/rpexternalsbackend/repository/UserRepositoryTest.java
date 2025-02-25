package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.model.value.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    void testSave(){
        assert userRepository != null;
        User savedUser = userRepository.save(buildTestUser());
        assert savedUser != null;
        assert savedUser.getId() != null;
    }
    private static User buildTestUser() {
        return User.builder()
                .name("negor")
                .surname("ergon")
                .email("Mipiaceilcock@gmail.it")
                .password(")!~)@!FRJfsgfsg")
                .userType(UserType.ADMIN)
                //.tickets(null)
                //.reservations(null)
                .build();
    }
}
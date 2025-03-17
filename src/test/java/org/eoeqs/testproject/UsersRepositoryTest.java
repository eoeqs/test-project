package org.eoeqs.testproject;

import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.models.enums.Gender;
import org.eoeqs.testproject.models.enums.Goal;
import org.eoeqs.testproject.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testCreateUserWithValidData() {
        Users user = new Users();
        user.setName("Анна");
        user.setEmail("anna@example.com");
        user.setAge(28);
        user.setWeight(55);
        user.setHeight(165);
        user.setGender(Gender.FEMALE);
        user.setGoal(Goal.WEIGHT_LOSS);

        Users savedUser = usersRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("Анна", savedUser.getName());
        assertEquals("anna@example.com", savedUser.getEmail());
    }
}
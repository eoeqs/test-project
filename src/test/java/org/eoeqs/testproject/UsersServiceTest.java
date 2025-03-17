package org.eoeqs.testproject;

import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.models.enums.Gender;
import org.eoeqs.testproject.models.enums.Goal;
import org.eoeqs.testproject.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsersServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testCalculateDailyCalorieNorm() {
        Users user = new Users();
        user.setGender(Gender.MALE);
        user.setWeight(70);
        user.setHeight(175);
        user.setAge(30);
        user.setGoal(Goal.MAINTENANCE);
        user = userService.createUser(user);
        double norm = user.calculateDailyCalorieNorm();
        double expected = (88.362 + (13.397 * 70) + (4.799 * 175) - (5.677 * 30));
        assertEquals(expected, norm, 0.1);
    }

}
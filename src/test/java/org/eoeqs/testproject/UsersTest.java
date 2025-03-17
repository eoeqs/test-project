package org.eoeqs.testproject;

import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.models.enums.Gender;
import org.eoeqs.testproject.models.enums.Goal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest {

    @Test
    public void testCalculateDailyCalorieNormMaleWeightGain() {
        Users user = new Users();
        user.setGender(Gender.MALE);
        user.setWeight(80);
        user.setHeight(180);
        user.setAge(35);
        user.setGoal(Goal.WEIGHT_GAIN);

        double expectedBMR = 88.362 + (13.397 * 80) + (4.799 * 180) - (5.677 * 35);
        double expectedNorm = expectedBMR * 1.2;

        assertEquals(expectedNorm, user.calculateDailyCalorieNorm(), 0.1);
    }
}

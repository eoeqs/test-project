package org.eoeqs.testproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eoeqs.testproject.models.enums.Gender;
import org.eoeqs.testproject.models.enums.Goal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Email(message = "Некорректный email")
    private String email;

    @Min(value = 1, message = "Возраст должен быть больше 0")
    @Max(value = 120, message = "Возраст должен быть меньше 120")
    private int age;

    @Min(value = 1, message = "Вес должен быть больше 0")
    @Max(value = 300, message = "Вес должен быть меньше 300")
    private double weight;

    @Min(value = 50, message = "Рост должен быть больше 50 см")
    @Max(value = 300, message = "Рост должен быть меньше 300 см")
    private double height;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Пол обязателен")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Цель обязательна")
    private Goal goal;


    public double calculateDailyCalorieNorm() {
        double bmr;
        if (gender == Gender.MALE) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
        return switch (goal) {
            case WEIGHT_LOSS -> bmr * 0.8;
            case WEIGHT_GAIN -> bmr * 1.2;
            default -> bmr;
        };
    }
}


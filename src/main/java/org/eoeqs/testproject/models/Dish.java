package org.eoeqs.testproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название блюда не может быть пустым")
    private String name;

    @Min(value = 0, message = "Калории не могут быть отрицательными")
    private double calories;

    @Min(value = 0, message = "Белки не могут быть отрицательными")
    private double proteins;

    @Min(value = 0, message = "Жиры не могут быть отрицательными")
    private double fats;

    @Min(value = 0, message = "Углеводы не могут быть отрицательными")
    private double carbs;


}
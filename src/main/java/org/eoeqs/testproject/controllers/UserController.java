package org.eoeqs.testproject.controllers;

import jakarta.validation.Valid;
import org.eoeqs.testproject.models.Users;
import org.eoeqs.testproject.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Users createUser(@Valid @RequestBody Users user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @Valid @RequestBody Users user) {
        return userService.updateUser(id, user);
    }
}
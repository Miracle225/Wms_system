package com.example.wms_system.controllers;

import com.example.wms_system.dto.UserDto;
import com.example.wms_system.enums.Role;
import com.example.wms_system.models.User;
import com.example.wms_system.payload.SignUpRequest;
import com.example.wms_system.services.AuthenticationService;
import com.example.wms_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getAllUsers(
            @RequestParam Optional<String> id,
            @RequestParam Optional<String> username,
            @RequestParam Optional<String> role,
            Model model) {
        List<User> users = userService.getAllUsers();
        if (id.isPresent() && !id.get().isBlank()) {
            try {
                Long longId = Long.parseLong(id.get());
                users = userService.getUserByIdToList(longId);
            } catch (IllegalArgumentException ex) {
                model.addAttribute("error", "Invalid user id type");
            }
        }
        if(username.isPresent() && !username.get().isBlank()){
            try {
                users = userService.getAllUsersByNamePart(username.get());
            }catch (IllegalArgumentException ex){
                model.addAttribute("error","Invalid username type");
            }
        }
        if (role.isPresent()){
            try{
                Role userRole = Role.valueOf(role.get());
                users = userService.getAllUsersByRole(userRole);
            }catch (IllegalArgumentException ex){
                model.addAttribute("error","Invalid role type");
            }
        }
        model.addAttribute("users", users);
        return "users";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }
    @GetMapping("/register")
    public String showCreateUserForm() {
        return "register";
    }
    @PostMapping("/register")
    ResponseEntity<?> createUser(@RequestBody @Valid SignUpRequest request) {
        try {
            User user = authenticationService.signUp(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping("/update/{id}")
    public String showUserUpdateForm(@PathVariable Long id,Model model){
        User user = userService.getById(id);
        model.addAttribute("user",user);
        model.addAttribute("roles",Role.values());
        return "update-user";
    }
    @PostMapping("/update/{id}")
    String updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}

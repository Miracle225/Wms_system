package com.example.wms_system.services;

import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.dto.UserDto;
import com.example.wms_system.enums.Role;
import com.example.wms_system.exceptions.OrderNotFoundException;
import com.example.wms_system.exceptions.UserAlreadyExists;
import com.example.wms_system.exceptions.UserDuplicateEmail;
import com.example.wms_system.exceptions.UserNotFoundException;
import com.example.wms_system.models.Order;
import com.example.wms_system.models.User;
import com.example.wms_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */



    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(UserDto userDto) {
        if (repository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExists();
        }
        if (repository.existsByEmail(userDto.getEmail())) {
            throw new UserDuplicateEmail(userDto.getEmail());
        }
        User user = userDto.toUserEntity();

        return repository.save(user);
    }

    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return updateUserFields(user,userDto);
    }
    public void deleteUserById(Long id) {
        repository.deleteById(id);
        log.info("Delete user by id: {}", id);
    }
    public User getById(Long id){
        Optional<User> loadUser = repository.findById(id);
        log.info("Loaded user with id: {}", id);
        return loadUser.orElseThrow(() -> {
            log.error("User with id: {} not found", id);
            return new UserNotFoundException(id);
        });
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public List<User> getAllUsers(){
        return printLogInfo(repository.findAll());
    }
    private User updateUserFields(User user, UserDto userDto) {
       User updatedUser = user.updateFields(userDto);
        log.info("Updated user with id: {}", updatedUser.getId());
        return updatedUser;
    }
    private List<User> printLogInfo(List<User> users) {
        log.info("Size of loaded users from database: {}", users.size());
        return users;
    }
/*
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }*/
}

package litte.example.demo.rest;

import litte.example.demo.entities.Role;
import litte.example.demo.entities.User;
import litte.example.demo.repository.RoleRepository;
import litte.example.demo.repository.UserRepository;
import litte.example.demo.userservice.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
public class UserRestController {

    private UserRepository userRepository;

    private UserService userService;

    public UserRestController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
    class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Exception ex) {
            super(ex);
        }
    }


    @GetMapping("/user")
    public Iterable<User> findAllUsers(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return userRepository.findByName(name);
        } else {
            return userRepository.findAll();
        }
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User newUser) {
        userRepository.save(newUser);
    }

    @DeleteMapping("/user/{uuid}")
    public void deleteUser(@PathVariable("uuid") UUID uuid) {
        try {
            userRepository.deleteById(uuid);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(ex);
        }
    }

    @PostMapping("/user/{uuid}/role")
    public void addRole(@PathVariable("uuid") UUID uuid, @RequestBody UUID roleId) {
        this.userService.addRole(uuid, roleId);
    }

    @DeleteMapping("/user/{uuid}/role/{roleId}")
    public void deleteRole(@PathVariable("uuid") UUID uuid, @PathVariable("roleId") UUID roleId) {
        this.userService.deleteRole(uuid, roleId);
    }
}

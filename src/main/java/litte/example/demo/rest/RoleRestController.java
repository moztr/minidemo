package litte.example.demo.rest;

import litte.example.demo.entities.Role;
import litte.example.demo.entities.User;
import litte.example.demo.repository.RoleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RoleRestController {

    private RoleRepository roleRepository;

    public RoleRestController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
    class RoleNotFoundException extends RuntimeException {
        public RoleNotFoundException(Exception ex) {
            super(ex);
        }
    }


    @GetMapping("/role")
    public Iterable<Role> findAllRoles(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return roleRepository.findByName(name);
        } else {
            return roleRepository.findAll();
        }
    }

    @PostMapping("/role")
    public void addRole(@RequestBody Role newRole) {
        roleRepository.save(newRole);
    }

    @DeleteMapping("/role/{uuid}")
    public void deleteRole(@PathVariable("uuid") UUID uuid) {
        try {
            roleRepository.deleteById(uuid);
        } catch (EmptyResultDataAccessException ex) {
            throw new RoleNotFoundException(ex);
        }
    }
}

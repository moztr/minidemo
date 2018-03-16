package litte.example.demo.userservice;

import litte.example.demo.entities.Role;
import litte.example.demo.entities.User;
import litte.example.demo.repository.RoleRepository;
import litte.example.demo.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addRole(UUID uuid, UUID roleId) {
        Role role = this.getRoleById(roleId);

        Optional<User> user = this.userRepository.findById(uuid);
        if (user.isPresent()) {
            Set<Role> userRoles = user.get().getRoles();
            userRoles.add(role);
        }

    }

    @Transactional
    public void deleteRole(UUID uuid, UUID roleId) {
        Role role = this.getRoleById(roleId);
        Optional<User> user = this.userRepository.findById(uuid);
        if (user.isPresent()) {
            Set<Role> userRoles = user.get().getRoles();
            userRoles.remove(role);
        }

    }

    private Role getRoleById(UUID roleId) {
        Optional<Role> role = this.roleRepository.findById(roleId);
        if (!role.isPresent()) {
            throw new RoleNotFoundException();
        }
        return role.get();
    }
}

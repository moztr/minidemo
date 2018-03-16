package litte.example.demo.repository;

import litte.example.demo.entities.Role;
import litte.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    List<Role> findByName(String name);
}

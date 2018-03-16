package litte.example.demo.repository;

import litte.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByName(String name);
}

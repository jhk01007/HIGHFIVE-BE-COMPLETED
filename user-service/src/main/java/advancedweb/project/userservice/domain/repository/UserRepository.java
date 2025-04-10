package advancedweb.project.userservice.domain.repository;

import advancedweb.project.userservice.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}

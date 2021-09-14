package sarafan.sarafan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sarafan.sarafan.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}

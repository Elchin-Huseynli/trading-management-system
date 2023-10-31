package express.az.tms.repository;

import express.az.tms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findAppUserByEmailAndIsEnable(String email, boolean isEnable);
    Boolean existsByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);

}
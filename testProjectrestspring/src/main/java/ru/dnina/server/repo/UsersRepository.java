package ru.dnina.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnina.server.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}

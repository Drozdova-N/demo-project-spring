package ru.dnina.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnina.server.models.Token;


import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByValue(String value);
    void removeTokenByValue(String value);
}

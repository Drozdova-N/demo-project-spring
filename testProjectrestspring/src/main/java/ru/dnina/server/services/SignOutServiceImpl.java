package ru.dnina.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dnina.server.models.Token;
import ru.dnina.server.repo.TokensRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SignOutServiceImpl implements SignOutService
{

    @Autowired
    TokensRepository tokensRepository;

    @Override
    @Transactional
    public void signOut() {
        String tokenValue = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Token> tokenOptional = tokensRepository.findTokenByValue(tokenValue);
        if(tokenOptional.isPresent()) {
            tokensRepository.removeTokenByValue(tokenValue);
            SecurityContextHolder.clearContext();
        }
    }
}

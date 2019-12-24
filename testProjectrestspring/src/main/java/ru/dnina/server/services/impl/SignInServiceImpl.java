package ru.dnina.server.services.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnina.server.forms.SignInForm;
import ru.dnina.server.models.Token;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.TokensRepository;
import ru.dnina.server.repo.UsersRepository;
import ru.dnina.server.services.SignInService;
import ru.dnina.server.transfer.TokenDto;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto signIn(SignInForm form) throws IllegalArgumentException {
        Optional<User> userOptional = usersRepository.findByLogin(form.getLogin());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(passwordEncoder.matches(form.getPassword(), user.getHashPassword())){
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();
                tokensRepository.save(token);
                return TokenDto.from(token);
            }
           else throw new IllegalArgumentException("Incorrect password");
        }
       else throw new IllegalArgumentException("User not found");
    }
}

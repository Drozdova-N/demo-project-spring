package ru.dnina.server.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.dnina.server.models.Token;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.TokensRepository;
import ru.dnina.server.security.token.TokenAuthentication;

import java.util.Optional;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokensRepository tokensRepository;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
      Optional<Token> tokenOptional = tokensRepository.findTokenByValue(tokenAuthentication.getName());

       if(tokenOptional.isPresent()){
           User user = tokenOptional.get().getUser();
           UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
           tokenAuthentication.setUserDetails(userDetails);
           tokenAuthentication.setAuthenticated(true);
           return tokenAuthentication;
       }
        else throw new IllegalArgumentException("bad token");

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}

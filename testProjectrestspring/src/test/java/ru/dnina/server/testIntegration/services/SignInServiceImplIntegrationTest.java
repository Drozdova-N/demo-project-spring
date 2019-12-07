package ru.dnina.server.testIntegration.services;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dnina.server.forms.SignInForm;
import ru.dnina.server.forms.SignInFormImpl;
import ru.dnina.server.models.Role;
import ru.dnina.server.models.Token;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.TokensRepository;
import ru.dnina.server.repo.UsersRepository;
import ru.dnina.server.services.SignInService;
import ru.dnina.server.services.SignInServiceImpl;
import ru.dnina.server.transfer.TokenDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SignInServiceImplIntegrationTest {

    @TestConfiguration
    static class SignInServiceImplTestContextConfiguration{

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SignInService signInServiceTest(){
            return new SignInServiceImpl();
        }
    }

    @Autowired
    @Qualifier("signInServiceTest")
    private SignInService signInService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private TokensRepository tokensRepository;

    @Before
    public void setUp(){
        User user = User.builder()
                .id((long) 1)
                .login("test@gmail.ru")
                .name("test")
                .hashPassword(passwordEncoder.encode("123456"))
                .phone("777-77-77")
                .role(Role.USER)
                .build();

        Token token = Token.builder()
                .user(user)
                .value(RandomStringUtils.random(10, true, true))
                .build();

        Mockito.when(usersRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        Mockito.when(tokensRepository.save(token)).thenReturn(token);
    }

    @Test
    public void whenLoginAndPasswordValid_thenReturnToken(){
        SignInForm signInForm = new SignInFormImpl("test@gmail.ru", "123456");
        TokenDto found = signInService.signIn(signInForm);
        assertThat(found).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLoginNotValid_thenUserNotFound(){
        SignInForm signInForm = new SignInFormImpl("badLogin@gmail.com", "123456");
        signInService.signIn(signInForm);

    }


    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordNotValid_thenUserNotFound(){
        SignInForm signInForm = new SignInFormImpl("test@gmail.ru", "123");
        signInService.signIn(signInForm);

    }
}

package ru.dnina.server.testIntegration.services;

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
import ru.dnina.server.forms.SignUpForm;
import ru.dnina.server.forms.impl.SignUpFormImpl;
import ru.dnina.server.models.Role;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.UsersRepository;
import ru.dnina.server.services.SignUpService;
import ru.dnina.server.services.impl.SignUpServiceImpl;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class SignUpServiceImplIntegrationTest {

    @TestConfiguration
    static class SignUpServiceImplTestContextConfiguration{

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SignUpService signUpServiceTest(){
            return  new SignUpServiceImpl();
        }

    }

    @Autowired
    @Qualifier("signUpServiceTest")
    private SignUpService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UsersRepository usersRepository;

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

        User otherUser = User
                .builder()
                .id((long) 1)
                .login("testOther@gmail.com")
                .name("testOther")
                .hashPassword(passwordEncoder.encode("123"))
                .phone("888-88-88")
                .role(Role.USER)
                .build();

        Mockito.when(usersRepository.findByLogin(otherUser.getLogin())).thenReturn(Optional.of(otherUser));
        Mockito.when(usersRepository.save(user)).thenReturn(user);
    }

    @Test
    public void whenSignUpFormValid_thenSignUp(){
        SignUpForm signUpForm = SignUpFormImpl.builder()
                .login("test@gmail.ru")
                .name("testOther")
                .password("123456")
                .phone("45645")
                .build();

        signUpService.signUp(signUpForm);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDuplicateLogin_thenNotSignUp(){
        SignUpForm signUpForm = SignUpFormImpl.builder()
                .login("testOther@gmail.com")
                .name("testOther")
                .password("123456")
                .phone("45645")
                .build();

        signUpService.signUp(signUpForm);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectForm_thenNotSignUp(){
        SignUpForm signUpForm = SignUpFormImpl.builder()
                .login("test@gmail.ru")
                .name("testOther")
                .phone("45645")
                .build();
        signUpService.signUp(signUpForm);
    }

}

package ru.dnina.server.testIntegration.controllers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dnina.server.controllers.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {
//
//
//    @TestConfiguration
//    static class UserControllerTestContextConfiguration{
//        @Bean
//        public PasswordEncoder passwordEncoder(){
//            return new BCryptPasswordEncoder();
//        }
//    }
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private TokensRepository tokensRepository;
//
//    @Test
//    public void givenUsers_whenGetUsers_thenResponseArray() throws Exception {
//
//        User user = User.builder()
//                .id((long) 1)
//                .login("test@gmail.ru")
//                .name("test")
//                .hashPassword(passwordEncoder.encode("123456"))
//                .phone("777-77-77")
//                .role(Role.USER)
//                .build();
//
//        User otherUser = User
//                .builder()
//                .id((long) 1)
//                .login("testOther@gmail.com")
//                .name("testOther")
//                .hashPassword(passwordEncoder.encode("123"))
//                .phone("888-88-88")
//                .role(Role.USER)
//                .build();
//
//        Token token = Token.builder()
//                .user(user)
//                .value(RandomStringUtils.random(10, true, true))
//                .build();
//
//        List<UserDto> users = Arrays.asList(UserDto.from(user), UserDto.from(otherUser));
//
//        given(userService.findAll()).willReturn(users);
//
//        mockMvc.perform(get("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("authentication" , token.getValue())
//        );
//
//    }
}

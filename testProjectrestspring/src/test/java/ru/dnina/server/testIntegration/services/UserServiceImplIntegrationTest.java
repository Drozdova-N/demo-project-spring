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
import ru.dnina.server.forms.UpdatePasswordForm;
import ru.dnina.server.forms.UpdateRoleForm;
import ru.dnina.server.forms.UpdateUserForm;
import ru.dnina.server.forms.impl.UpdatePasswordFormImpl;
import ru.dnina.server.forms.impl.UpdateRoleFormImpl;
import ru.dnina.server.forms.impl.UpdateUserFormImpl;
import ru.dnina.server.models.Role;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.UsersRepository;
import ru.dnina.server.services.UserService;
import ru.dnina.server.services.impl.UserServiceImpl;
import ru.dnina.server.transfer.UserDto;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserService userServiceTest() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    @Qualifier("userServiceTest")
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UsersRepository usersRepository;

    @Before
    public void setUp() {
        User user = User.builder()
                .id((long) 1)
                .login("test@gmail.ru")
                .name("test")
                .hashPassword(passwordEncoder.encode("123456"))
                .phone("777-77-77")
                .role(Role.USER)
                .build();

        User updateUser = User
                .builder()
                .id((long) 1)
                .login("test@gmail.com")
                .name("updateTest")
                .hashPassword(passwordEncoder.encode("123456789"))
                .phone("888-88-88")
                .role(Role.ADMIN)
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
        Mockito.when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(usersRepository.save(user)).thenReturn(updateUser);
        Mockito.when(usersRepository.findByLogin(otherUser.getLogin())).thenReturn(Optional.of(otherUser));

    }

    @Test
    public void whenValidId_thenUserShouldBeFound() {
        long id = 1;
        UserDto found = userService.findUserById(id);
        assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenNotValidId_thenUserShouldBeNotFound() {
        long id = 0;
        try {
            userService.findUserById(id);
        } catch (IllegalArgumentException exp) {
            assertThat(exp.getMessage()).isEqualTo("User not found");
        }
    }

    @Test
    public void whenValidId_thenUserShouldBeFoundForUpdate() {
        long id = 1;
        UpdateUserForm userForm = UpdateUserFormImpl
                .builder()
                .login("test@gmail.com")
                .name("updateTest")
                .phone("888-88-88")
                .build();
        Optional<User> userOptional = usersRepository.findById(id);
        User user = userOptional.get();
        user.setLogin(userForm.getLogin());
        user.setName(userForm.getName());
        user.setPhone(userForm.getPhone());
        UserDto userDto = UserDto.from(user);
        UserDto found = userService.updateUser(id, userForm);
        assertThat(found).isEqualTo(userDto);
    }

    @Test
    public void whenNotValidId_thenUserBeNotFoundForUpdate() {
        long id = 0;
        UpdateUserForm userForm = UpdateUserFormImpl
                .builder()
                .login("test@gmail.com")
                .name("updateTest")
                .phone("888-88-88")
                .build();

        try {
            userService.updateUser(id, userForm);
        } catch (IllegalArgumentException exp) {
            assertThat(exp.getMessage()).isEqualTo("User not found");
        }
    }

    @Test
    public void whenIncorrectUpdateForm_thenUserNotUpdate() {
        long id = 1;
        UpdateUserForm userForm = UpdateUserFormImpl
                .builder()
                .login("test@gmail.com")
                .phone("888-88-88")
                .build();


        try {
            userService.updateUser(id, userForm);
        } catch (IllegalArgumentException exp) {
            assertThat(exp.getMessage()).isEqualTo("Empty field");
        }
    }

    @Test
    public void whenDuplicateLogin_thenUserNotUpdate() {
        long id = 1;
        UpdateUserForm userForm = UpdateUserFormImpl
                .builder()
                .login("testOther@gmail.com")
                .name("updateTest")
                .phone("888-88-88")
                .build();

        try {
            userService.updateUser(id, userForm);
        } catch (IllegalArgumentException exp) {
            assertThat(exp.getMessage()).isEqualTo("User with that username exists");
        }
    }


    @Test
    public void whenIncorrectRoleForm_thenRoleUserNotUpdate(){
        long id =1;
        UpdateRoleForm  roleForm = UpdateRoleFormImpl.builder().build();
        try {
            userService.updateRoleUser(id, roleForm);
        }
        catch (IllegalArgumentException exp) {
            assertThat(exp.getMessage()).isEqualTo("Empty field");
        }
    }

    @Test
    public void whenNotValidId_thenRoleUserNotUpdate(){
        long id =0;
        UpdateRoleForm  roleForm = UpdateRoleFormImpl.builder().role(Role.USER).build();
        try {
            userService.updateRoleUser(id, roleForm);
        }
        catch (IllegalArgumentException exp) {
            assertThat(exp.getMessage()).isEqualTo("User not found");
        }
    }

    @Test
    public void whenValidData_thenRoleUserUpdate(){
        long id =1;
        UpdateRoleForm  roleForm = UpdateRoleFormImpl.builder().role(Role.ADMIN).build();
        Optional<User> optionalUser = usersRepository.findById(id);
        User user = optionalUser.get();
        user.setRole(roleForm.getRole());
        UserDto userDto = UserDto.from(user);
        UserDto found = userService.updateRoleUser(id, roleForm);
        assertThat(found).isEqualTo(userDto);
    }

    @Test
    public void whenIncorrectPasswordForm_thenPasswordUserNotUpdate(){
        long id = 1;
        UpdatePasswordForm passwordForm = UpdatePasswordFormImpl.builder()
                .oldPassword("")
                .newPassword("654123")
                .build();
        try {
            userService.updatePasswordUser(id, passwordForm);
        }
        catch (IllegalArgumentException exp){
            assertThat(exp.getMessage()).isEqualTo("Empty field");
        }
    }

    @Test
    public void whenNotValidId_thenPasswordUserNotUpdate(){
        long id = 0;
        UpdatePasswordForm passwordForm = UpdatePasswordFormImpl.builder()
                .oldPassword("123456")
                .newPassword("123456789")
                .build();
        try {
            userService.updatePasswordUser(id, passwordForm);
        }
        catch (IllegalArgumentException exp){
            assertThat(exp.getMessage()).isEqualTo("User not found");
        }
    }
    @Test
    public void whenOldPasswordIsNotCorrect_thenPasswordUserNotUpdate(){
        long id = 1;
        UpdatePasswordForm passwordForm = UpdatePasswordFormImpl.builder()
                .oldPassword("12345678")
                .newPassword("123456789")
                .build();
        try {
            userService.updatePasswordUser(id, passwordForm);
        }
        catch (IllegalArgumentException exp){
            assertThat(exp.getMessage()).isEqualTo("The old password was not entered correctly");
        }
    }

    @Test
    public void whenValidData_thenPasswordUserUpdate(){
        long id = 1;
        UpdatePasswordForm passwordForm = UpdatePasswordFormImpl.builder()
                .oldPassword("123456")
                .newPassword("123456789")
                .build();
        userService.updatePasswordUser(id, passwordForm);
        Optional<User> optionalUser = usersRepository.findById(id);
        optionalUser.ifPresent(user ->assertThat(passwordEncoder.matches(passwordForm.getNewPassword(), user.getHashPassword())));

    }
}


package ru.dnina.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnina.server.forms.UpdatePasswordForm;
import ru.dnina.server.forms.UpdateRoleForm;
import ru.dnina.server.forms.UpdateUserForm;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.UsersRepository;
import ru.dnina.server.security.details.UserDetailsImpl;
import ru.dnina.server.services.UserService;
import ru.dnina.server.transfer.UserDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto findUserById(Long id) throws IllegalArgumentException {
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            return UserDto.from(userOptional.get());
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }


    @Override
    public UserDto findAuthorizedUser() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails();
    User currentUser = userDetails.getUser();
        return UserDto.from(currentUser);
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserForm form) throws IllegalArgumentException {

        //check correct form
        if (form.getLogin() == null || form.getName() == null){
            throw new IllegalArgumentException("Empty field");
        }

        Optional<User> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            try {
                checkDuplicateUser(user, form);
                usersRepository.save(apply(user, form));
                return UserDto.from(usersRepository.findById(id).get());
            } catch (IllegalArgumentException exp) {
                throw exp;
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public UserDto updateRoleUser(Long id, UpdateRoleForm form) {

        //check correct form
        if (form.getRole() == null) {
            throw new IllegalArgumentException("Empty field");
        }
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(form.getRole());
            usersRepository.save(user);
            return UserDto.from(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public void updatePasswordUser(Long id, UpdatePasswordForm form) {
        //check correct form
      if(form.getOldPassword()== null || form.getNewPassword()==null){
          throw  new IllegalArgumentException("Empty field");
      }

      Optional<User> optionalUser = usersRepository.findById(id);

      if(optionalUser.isPresent()){
          User user  = optionalUser.get();
          if(passwordEncoder.matches(form.getOldPassword(), user.getHashPassword())){
              user.setHashPassword(passwordEncoder.encode(form.getNewPassword()));
              usersRepository.save(user);
          }
          else {
              throw new IllegalArgumentException("The old password was not entered correctly");
          }
      }
          else {
              throw new IllegalArgumentException("User not found");
      }

    }

    private User apply(User user, UpdateUserForm form) {
        user.setLogin(form.getLogin());
        user.setName(form.getName());
        user.setPhone(form.getPhone());
        return user;
    }

    private void checkDuplicateUser(User user, UpdateUserForm form) throws IllegalArgumentException {
        Optional<User> checkDuplicate = usersRepository.findByLogin(form.getLogin());
        if (checkDuplicate.isPresent()) {
            if (!checkDuplicate.get().getLogin().equals(user.getLogin())) {
                throw new IllegalArgumentException("User with that username exists");
            }
        }
    }
}

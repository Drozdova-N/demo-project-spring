package ru.dnina.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnina.server.forms.SignUpForm;
import ru.dnina.server.models.Role;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void signUp(SignUpForm form) throws IllegalArgumentException {
        if(form.getLogin() == null
                || form.getName()== null
                || form.getPassword()==null) throw new IllegalArgumentException("empty field");
            if(usersRepository.findByLogin(form.getLogin()).isPresent())
          throw new IllegalArgumentException("user with that username exists");
     String hashPassword = passwordEncoder.encode(form.getPassword());
     User newUser = User
                 .builder()
                 .login(form.getLogin())
                 .name(form.getName())
                 .phone(form.getPhone())
                 .hashPassword(hashPassword)
                 .role(Role.USER)
                 .build();
         usersRepository.save(newUser);
    }
}

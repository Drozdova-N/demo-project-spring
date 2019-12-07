package ru.dnina.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnina.server.forms.UpdateUserForm;
import ru.dnina.server.models.User;
import ru.dnina.server.repo.UsersRepository;
import ru.dnina.server.transfer.UserDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto findUserById(Long id) throws IllegalArgumentException {
        Optional<User> userOptional = usersRepository.findById(id);
        if(userOptional.isPresent())
             return UserDto.from(userOptional.get());

       else throw  new IllegalArgumentException("user not found");
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserForm form) throws IllegalArgumentException{

        // correct form
        if(form.getLogin()== null || form.getName()==null || form.getRole()== null)
            throw new IllegalArgumentException("empty field");

        Optional<User> userOptional = usersRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();

            // duplicate login check
            Optional<User> checkDuplicate = usersRepository.findByLogin(form.getLogin());
            if(checkDuplicate.isPresent())
                if(!checkDuplicate.get().getLogin().equals(user.getLogin()))
                    throw new IllegalArgumentException("user with that username exists");

            user.setLogin(form.getLogin());
            user.setName(form.getName());
            user.setPhone(form.getPhone());
            user.setRole(form.getRole());
            if(form.getPassword()!= null) user.setHashPassword(passwordEncoder.encode(form.getPassword()));
            usersRepository.save(user);
            return UserDto.from(user);
        }
        else throw new IllegalArgumentException("user not found");
    }
}

package ru.dnina.server.services;

import ru.dnina.server.forms.UpdateUserForm;
import ru.dnina.server.transfer.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto findUserById(Long id);

    UserDto updateUser(Long id, UpdateUserForm form);
}

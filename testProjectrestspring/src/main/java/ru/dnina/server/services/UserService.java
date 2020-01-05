package ru.dnina.server.services;

import ru.dnina.server.forms.UpdatePasswordForm;
import ru.dnina.server.forms.UpdateRoleForm;
import ru.dnina.server.forms.UpdateUserForm;
import ru.dnina.server.models.User;
import ru.dnina.server.transfer.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto findUserById(Long id);

    UserDto updateUser(Long id, UpdateUserForm form);

    UserDto updateRoleUser(Long id, UpdateRoleForm form);

    void updatePasswordUser(Long id, UpdatePasswordForm form);

    UserDto findAuthorizedUser();
}

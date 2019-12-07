package ru.dnina.server.util;

import ru.dnina.server.forms.UpdateUserFormImpl;
import ru.dnina.server.transfer.UserDto;

public class ConvertModel {

    public static UserDto UserFormInUserDto(Long id, UpdateUserFormImpl updateUserForm) {

        UserDto userDto = UserDto.builder()
                .id(id)
                .login(updateUserForm.getLogin())
                .name(updateUserForm.getName())
                .phone(updateUserForm.getPhone())
                .role(updateUserForm.getRole())
                .build();
        return userDto;

    }
}

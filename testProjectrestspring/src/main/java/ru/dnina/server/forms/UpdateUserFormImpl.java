package ru.dnina.server.forms;

import lombok.Builder;
import lombok.Data;
import ru.dnina.server.models.Role;

@Data
@Builder
public class UpdateUserFormImpl implements UpdateUserForm {

    private String login;
    private String name;
    private String phone;
    private String password;
    private Role role;
}

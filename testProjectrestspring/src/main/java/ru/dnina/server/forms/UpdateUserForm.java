package ru.dnina.server.forms;

import ru.dnina.server.models.Role;

public interface UpdateUserForm {

    String getLogin();

    String getName();

    String getPhone();

    String getPassword();

    Role getRole();

}

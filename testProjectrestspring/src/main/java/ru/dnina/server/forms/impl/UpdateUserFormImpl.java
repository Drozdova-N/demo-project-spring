package ru.dnina.server.forms.impl;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.dnina.server.forms.UpdateUserForm;


@Data
@Builder
@AllArgsConstructor
public class UpdateUserFormImpl implements UpdateUserForm {
    private String login;
    private String name;
    private String phone;
}

package ru.dnina.server.forms.impl;

import lombok.Builder;
import lombok.Data;
import ru.dnina.server.forms.SignUpForm;

@Data
@Builder
public class SignUpFormImpl implements SignUpForm {

    private String login;
    private String name;
    private String phone;
    private String password;

}

package ru.dnina.server.forms.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.dnina.server.forms.SignInForm;

@Data
@Builder
@AllArgsConstructor
public class SignInFormImpl implements SignInForm {
    private String login;
    private String password;
}

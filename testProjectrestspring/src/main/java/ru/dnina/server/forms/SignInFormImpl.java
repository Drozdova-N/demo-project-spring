package ru.dnina.server.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignInFormImpl implements SignInForm{
    private String login;
    private String password;
}

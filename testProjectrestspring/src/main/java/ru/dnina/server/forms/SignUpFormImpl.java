package ru.dnina.server.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpFormImpl implements SignUpForm{

    private String login;
    private String name;
    private String phone;
    private String password;

}

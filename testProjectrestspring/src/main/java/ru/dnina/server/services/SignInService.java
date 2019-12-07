package ru.dnina.server.services;

import ru.dnina.server.forms.SignInForm;
import ru.dnina.server.transfer.TokenDto;

public interface SignInService {
   TokenDto signIn(SignInForm form);
}

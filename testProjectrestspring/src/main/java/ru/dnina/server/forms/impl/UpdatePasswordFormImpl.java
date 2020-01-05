package ru.dnina.server.forms.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.dnina.server.forms.UpdatePasswordForm;

@Data
@Builder
@AllArgsConstructor
public class UpdatePasswordFormImpl implements UpdatePasswordForm {
    private String oldPassword;
    private String newPassword;
}

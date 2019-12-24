package ru.dnina.server.forms.impl;

import lombok.Builder;
import lombok.Data;
import ru.dnina.server.forms.UpdateRoleForm;
import ru.dnina.server.models.Role;

@Data
@Builder
public class UpdateRoleFormImpl implements UpdateRoleForm {
    private Role role;
}

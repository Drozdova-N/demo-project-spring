package ru.dnina.server.forms.impl;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnina.server.forms.UpdateRoleForm;
import ru.dnina.server.models.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleFormImpl implements UpdateRoleForm {
    private Role role;
}

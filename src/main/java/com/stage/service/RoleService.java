package com.stage.service;

import com.stage.model.Role;

import java.util.List;

/**
 * Created by wital on 04.02.2017.
 */
public interface RoleService {
    public void addRole(Role role);

    public void updateRole(Role role);

    public void removeRole(int id);

    public Role getRoleId(int id);

    public List<Role> listRole();
}

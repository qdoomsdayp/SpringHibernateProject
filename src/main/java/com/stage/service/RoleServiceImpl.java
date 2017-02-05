package com.stage.service;

import com.stage.dao.RoleDAO;
import com.stage.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wital on 04.02.2017.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        this.roleDAO.addRole(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
this.roleDAO.updateRole(role);
    }

    @Override
    @Transactional
    public void removeRole(int id) {
this.roleDAO.removeRole(id);
    }

    @Override
    @Transactional
    public Role getRoleId(int id) {
        return this.roleDAO.getRoleId(id);
    }

    @Override
    @Transactional
    public List<Role> listRole() {
        return this.roleDAO.listRole();
    }
}

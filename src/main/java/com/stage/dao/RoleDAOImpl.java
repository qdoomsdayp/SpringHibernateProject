package com.stage.dao;

import com.stage.model.Role;
import com.stage.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wital on 04.02.2017.
 */
@Repository("roleDao")
public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);
    private SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRole(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(role);
        logger.info("Role ADD: " + role);
    }

    @Override
    public void updateRole(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(role);
        logger.info("Role UPDATE: " + role);
    }

    @Override
    public void removeRole(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.load(Role.class,new Integer(id));
        if (role!=null){
            session.delete(role);
        }
        logger.info("Role DELETE: " + role);
    }

    @Override
    public Role getRoleId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.load(Role.class,new Integer(id));
        logger.info("Role GET: " + role);
        return role;
    }

  //  @Transactional
    public List<Role> listRole() {

        @SuppressWarnings("unchecked")

        List<Role> listRole = (List<Role>) sessionFactory.getCurrentSession()
                .createCriteria(Role.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listRole;
    }
}

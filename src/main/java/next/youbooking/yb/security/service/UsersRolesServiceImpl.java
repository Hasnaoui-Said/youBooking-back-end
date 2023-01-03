package next.youbooking.yb.security.service;

import next.youbooking.yb.security.dao.UsersRolesDao;
import next.youbooking.yb.security.models.entity.Role;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.models.entity.UsersRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersRolesServiceImpl {
    @Autowired
    UsersRolesDao usersRolesDao;

    public UsersRoles findByRoleName(String role) {
        return usersRolesDao.findByRoleName(role);
    }
    public List<UsersRoles> findAll() {
        return usersRolesDao.findAll();
    }


    public UsersRoles save(Role role, User user) {
        // To Do : check if fin by role name and user username already exist
        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setRole(role);
        usersRoles.setUsername(user);
        return usersRolesDao.save(usersRoles);
    }
}

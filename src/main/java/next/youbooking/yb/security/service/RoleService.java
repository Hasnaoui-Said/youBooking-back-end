package next.youbooking.yb.security.service;

import next.youbooking.yb.security.dao.RoleDao;
import next.youbooking.yb.security.models.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public Role findByName(String role) {
        return roleDao.findByName(role);
    }
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}

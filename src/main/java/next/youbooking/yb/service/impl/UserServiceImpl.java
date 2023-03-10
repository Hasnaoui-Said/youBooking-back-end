package next.youbooking.yb.service.impl;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.repository.UserRep;
import next.youbooking.yb.security.models.entity.Role;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.models.entity.UsersRoles;
import next.youbooking.yb.security.service.RoleService;
import next.youbooking.yb.security.service.UsersRolesServiceImpl;
import next.youbooking.yb.service.UserService;
import next.youbooking.yb.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRep userRep;
    @Autowired
    UsersRolesServiceImpl usersRolesService;
    @Autowired
    RoleService roleService;

    @Override
    public User findByUuid(UUID uuid) {
        return userRep.findByUuid(uuid);
    }

    @Override
    public User findByCin(String cin) {
        return userRep.findByCin(cin);
    }

    @Override
    public User findByEmail(String email) {
        return userRep.findByEmail(email);
    }

    @Override
    public int deleteByUuid(UUID uuid) {
        return userRep.deleteByUuid(uuid);
    }

    @Override
    public List<User> findAll() {
        return userRep.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRep.findAll(pageable);
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRep.findAll(pageRequest);
    }

    @Override
    public User save(User user, String role) {
        if (UtilString.isEmpty(user.getEmail()) || this.existsByEmail(user.getEmail()))
            throw new BadRequestException("Email is token or required!!");
        if (UtilString.isEmpty(user.getUsername()) || this.existsByUsername(user.getUsername()))
            throw new BadRequestException("Username is token or required!!");
        if (UtilString.isEmpty(user.getPassword()))
            throw new BadRequestException("Password is token or required!!");
        user.setUuid(UUID.randomUUID());
        user.setPassword(UtilString.passwordEncoder(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        if (role.equals("client"))
            user.setAccountState("ACTIVATED");
        else
            user.setAccountState("PENDING");

        Role roleObject = roleService.findByName(role.toUpperCase());
        if (roleObject == null) {
            throw new BadRequestException("Error leur de l'insertion!");
        }
        User userNv = userRep.save(user);
        try {
            UsersRoles usersRoles = usersRolesService.save(roleObject, userNv);
            userNv.setRoles(new ArrayList<>());
            userNv.getRoles().add(usersRoles);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
        return userNv;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRep.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRep.existsByEmail(email);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public int deleteByUsername(String username) {
        return userRep.deleteByUsername(username);
    }

    @Override
    public int deleteByEmail(String email) {
        return userRep.deleteByEmail(email);
    }

    @Override
    @Transactional
    public User changeState(UUID uuid, String state) {
        User user = this.findByUuid(uuid);
        if (user == null)
            throw new BadRequestException("user with this parameter not fount!!");
        user.setAccountState(state);
        return user;
    }
}
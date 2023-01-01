package next.youbooking.yb.service.impl;

import next.youbooking.yb.repository.UserRep;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRep userRep;

    @Override
    public User findByUuid(String uuid) {
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
    public int deleteByUuid(String uuid) {
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
    public User save(User entity) {
        return userRep.save(entity);
    }
}
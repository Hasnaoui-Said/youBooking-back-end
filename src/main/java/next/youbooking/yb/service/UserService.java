package next.youbooking.yb.service;

import next.youbooking.yb.security.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findByUuid(String uuid);

    User findByCin(String cin);

    User findByEmail(String email);

    int deleteByUuid(String uuid);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Page<User> findAll(PageRequest pageRequest);

//    User save(User entity);
    User save(User user, String role);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User update(User user);

    int deleteByUsername(String username);

    int deleteByEmail(String email);
}

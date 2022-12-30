package next.youbooking.yb.repository;

import next.youbooking.yb.security.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRep extends JpaRepository<User, UUID> {
    User findByUuid(String uuid);
    User findByCin(String cin);
    User findByEmail(String email);
    int deleteByUuid(String uuid);
}

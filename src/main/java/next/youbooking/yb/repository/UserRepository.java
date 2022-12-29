package next.youbooking.yb.repository;

import next.youbooking.yb.security.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

}

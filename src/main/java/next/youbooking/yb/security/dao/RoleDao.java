package next.youbooking.yb.security.dao;

import next.youbooking.yb.security.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {
    Role findByName(String role);
}

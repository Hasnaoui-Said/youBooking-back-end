package next.youbooking.yb.security.dao;

import next.youbooking.yb.security.models.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesDao extends JpaRepository<UsersRoles, Long> {
    UsersRoles findByRoleName(String role);
}

package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRep extends JpaRepository<Country, UUID> {
    Country findByCode(String code);
    int deleteByCode(String code);
    Country findByName(String name);
    int deleteByName(String name);
}

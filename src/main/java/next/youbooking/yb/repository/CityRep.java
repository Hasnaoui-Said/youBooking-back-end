package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRep extends JpaRepository<City, UUID> {
    City findByName(String name);
    int deleteByName(String name);
    City findByZipCode(String code);
    int deleteByZipCode(String code);
    List<City> findByCountryName(String name);
    Page<City> findByCountryName(String name, PageRequest pageRequest);

    boolean existsByZipCode(String code);

    boolean existsByName(String name);
}

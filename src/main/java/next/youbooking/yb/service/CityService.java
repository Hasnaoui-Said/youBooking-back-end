package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
    City findByName(String name);

    int deleteByName(String name);

    City findByZipCode(String code);

    int deleteByZipCode(String code);

    List<City> findByCountryName(String name);

    Page<City> findByCountryName(String name, PageRequest pageRequest);

    List<City> findAll();

    Page<City> findAll(Pageable pageable);

    Page<City> findAll(PageRequest pageRequest);

    City save(City city);

    City update(City city);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}

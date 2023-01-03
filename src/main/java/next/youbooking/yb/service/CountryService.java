package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {
    Country findByCode(String code);

    int deleteByCode(String code);

    Country findByName(String name);

    int deleteByName(String name);

    List<Country> findAll();

    Page<Country> findAll(Pageable pageable);

    Page<Country> findAll(PageRequest pageRequest);

    Country save(Country country);

    Country update(Country country);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}

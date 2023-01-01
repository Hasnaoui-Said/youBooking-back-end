package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.Country;
import next.youbooking.yb.repository.CountryRep;
import next.youbooking.yb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRep countryRep;

    @Override
    public Country findByCode(String code) {
        return countryRep.findByCode(code);
    }

    @Override
    public int deleteByCode(String code) {
        return countryRep.deleteByCode(code);
    }

    @Override
    public Country findByName(String name) {
        return countryRep.findByName(name);
    }

    @Override
    public int deleteByName(String name) {
        return countryRep.deleteByName(name);
    }

    @Override
    public List<Country> findAll() {
        return countryRep.findAll();
    }

    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryRep.findAll(pageable);
    }
    @Override
    public Page<Country> findAll(PageRequest pageRequest) {
        return countryRep.findAll(pageRequest);
    }

    @Override
    public Country save(Country country) {
        return countryRep.save(country);
    }
}
package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.City;
import next.youbooking.yb.repository.CityRep;
import next.youbooking.yb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRep cityRep;

    @Override
    public City findByName(String name) {
        return cityRep.findByName(name);
    }

    @Override
    public int deleteByName(String name) {
        return cityRep.deleteByName(name);
    }

    @Override
    public City findByZipCode(String code) {
        return cityRep.findByZipCode(code);
    }

    @Override
    public int deleteByZipCode(String code) {
        return cityRep.deleteByZipCode(code);
    }

    @Override
    public List<City> findByCountryName(String name) {
        return cityRep.findByCountryName(name);
    }

    @Override
    public Page<City> findByCountryName(String name, PageRequest pageRequest) {
        return cityRep.findByCountryName(name, pageRequest);
    }

    @Override
    public List<City> findAll() {
        return cityRep.findAll();
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRep.findAll(pageable);
    }
    @Override
    public Page<City> findAll(PageRequest pageRequest) {
        return cityRep.findAll(pageRequest);
    }

    @Override
    public City save(City city) {
        return cityRep.save(city);
    }
}
package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.City;
import next.youbooking.yb.repository.CityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl {
    @Autowired
    CityRep cityRep;

    public City findByName(String name) {
        return cityRep.findByName(name);
    }

    public int deleteByName(String name) {
        return cityRep.deleteByName(name);
    }

    public City findByZipCode(String code) {
        return cityRep.findByZipCode(code);
    }

    public int deleteByZipCode(String code) {
        return cityRep.deleteByZipCode(code);
    }

    public List<City> findByCountryName(String name) {
        return cityRep.findByCountryName(name);
    }

    public Page<City> findByCountryName(String name, PageRequest pageRequest) {
        return cityRep.findByCountryName(name, pageRequest);
    }

    public List<City> findAll() {
        return cityRep.findAll();
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRep.findAll(pageable);
    }

    public City save(City city) {
        return cityRep.save(city);
    }
}
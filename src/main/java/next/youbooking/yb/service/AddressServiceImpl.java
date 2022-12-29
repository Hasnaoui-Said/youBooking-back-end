package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Address;
import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.repository.AddressRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRep addressRep;

    @Override
    public Address findByUuid(String uuid) {
        return addressRep.findByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return addressRep.deleteByUuid(uuid);
    }

    @Override
    public Address findByHotelName(String name) {
        return addressRep.findByHotelName(name);
    }

    @Override
    public List<Address> findAllByCityName(String name) {
        return addressRep.findAllByCityName(name);
    }

    @Override
    public List<Address> findAll() {
        return addressRep.findAll();
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return addressRep.findAll(pageable);
    }

    @Override
    public  Address save(Address address) {
        if (existsByAddress(address.getAddress()))
            throw new BadRequestException("Address with this parameter already exist!!");
        if (address == null)
            throw new BadRequestException("Address null!!");
        if (address.getAddress().equals(""))
            throw new BadRequestException("Address required!!");
        address.setUuid(UUID.randomUUID());

        return addressRep.save(address);
    }

    @Override
    public boolean existsByAddress(String address) {
        return addressRep.existsByAddress(address);
    }
}

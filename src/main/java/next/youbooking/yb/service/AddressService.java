package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {
    Address findByUuid(String uuid);

    int deleteByUuid(String uuid);

    Address findByHotelName(String uuid);

    List<Address> findAllByCityName(String name);

    List<Address> findAll();

    Page<Address> findAll(Pageable pageable);

    Address save(Address address);

    boolean existsByAddress(String address);
}

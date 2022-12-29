package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface AddressRep extends JpaRepository<Address, UUID> {
    Address findByUuid(String uuid);
    int deleteByUuid(String uuid);
    boolean existsByAddress(String address);
    Address findByHotelName(String name);
    List<Address> findAllByCityName(String name);
}

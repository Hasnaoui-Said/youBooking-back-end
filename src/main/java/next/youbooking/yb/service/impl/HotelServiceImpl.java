package next.youbooking.yb.service.impl;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.models.entity.*;
import next.youbooking.yb.models.enums.StateHotel;
import next.youbooking.yb.models.enums.StatusRoom;
import next.youbooking.yb.models.vo.AddressVo;
import next.youbooking.yb.models.vo.BedRoomVo;
import next.youbooking.yb.models.vo.HotelVo;
import next.youbooking.yb.repository.HotelRep;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.service.UserDetailsServiceImpl;
import next.youbooking.yb.service.CityService;
import next.youbooking.yb.service.HotelService;
import next.youbooking.yb.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRep hotelRep;
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    CityService cityService;
    @Autowired
    TypeRoomService typeRoomService;

    @Override
    public Hotel findByName(String name) {
        return hotelRep.findByName(name);
    }

    @Override
    public Hotel findByStateHotel(String stateHotel) {
        return hotelRep.findByStateHotel(stateHotel);
    }

    @Override
    public Hotel findByAddress(String address) {
        return hotelRep.findByAddress(address);
    }

    @Override
    public int deleteByName(String name) {
        return hotelRep.deleteByName(name);
    }

    @Override
    public Hotel findByUuid(String uuid) {
        return hotelRep.findByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return hotelRep.deleteByUuid(uuid);
    }

    @Override
    public List<Hotel> findAllByUserUsername(String username) {
        return hotelRep.findAllByUserUsername(username);
    }

    @Override
    public Page<Hotel> findAllByUserUsername(String username, PageRequest pageRequest) {
        return hotelRep.findAllByUserUsername(username, pageRequest);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRep.findAll();
    }

    @Override
    public List<Hotel> findAll(String username) {
        return hotelRep.findAllByUserUsername(username);
    }

    @Override
    public Page<Hotel> findAll(Pageable pageable) {
        return hotelRep.findAll(pageable);
    }

    @Override
    public Page<Hotel> findAll(PageRequest pageRequest) {
        return hotelRep.findAll(pageRequest);
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRep.save(hotel);
    }

    @Override
    public Hotel findByCode(String code) {
        return null;
    }

    @Override
    public Hotel update(Hotel country) {
        return null;
    }

    @Override
    public boolean existsByName(String name) {
        return hotelRep.existsByName(name);
    }

    @Override
    public boolean existsByUuid(String uuid) {
        return hotelRep.existsByUuid(uuid);
    }

    @Override
    public Hotel saveHotel(HotelVo hotel, String username) {
        Hotel hotel1 = new Hotel();
        User user = this.checkUser(username);
        hotel1.setUser(user);

        Address address = this.getAddressFromHotel(hotel.getAddress());

        ArrayList<BedRoom> bedRooms = getBedRoom(hotel.getBedRooms());
        hotel1.setBedRooms(bedRooms);

        hotel1.setAddress(address);
        hotel1.setUuid(UUID.randomUUID());
        hotel1.setName(hotel.getName());
        hotel1.setDescription(hotel.getDescription());
        hotel1.setStateHotel(StateHotel.OPEN);

//        return this.hotelRep.save(hotel1);
        return hotel1;
    }

    private ArrayList<BedRoom> getBedRoom(List<BedRoomVo> bedRoomsVo) {
        ArrayList<BedRoom> bedRooms = new ArrayList<>();
        bedRoomsVo.forEach(roomVo -> {
            BedRoom room = new BedRoom();
            room.setUuid(UUID.randomUUID());
            room.setName(roomVo.getName());
            room.setNumberOfBeds(roomVo.getNumberOfBeds());
            room.setPrice(roomVo.getPrice());
            TypeRoom typeRoom = this.typeRoomService.findByUuid(roomVo.getTypeRoom());
            room.setTypeRoom(typeRoom);
            room.setStatusRoom(StatusRoom.AVAILABLE);
            bedRooms.add(room);
        });
        return bedRooms;
    }

    public Address getAddressFromHotel(AddressVo addressVo) {
        Address address = new Address();
        address.setUuid(UUID.randomUUID());
        City city = this.cityService.findByName(addressVo.getCity());
        address.setCity(city);
        address.setStreet(addressVo.getStreet());
        address.setAddress(addressVo.getAddress());
        address.setAddress(addressVo.getAddress());
        return address;
    }

    public User checkUser(String name) {
        User user = this.userService.findByUsername(name);
        if (user == null)
            throw new BadRequestException("Username not found");
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(usersRoles -> roles.add(usersRoles.getRole().getName()));
        if (!roles.contains("MANAGER"))
            throw new BadRequestException("No authorize");

        return user;
    }
}
package next.youbooking.yb.service.impl;

import next.youbooking.yb.exception.BadRequestException;
import next.youbooking.yb.exception.ServiceUnavailableException;
import next.youbooking.yb.models.entity.*;
import next.youbooking.yb.models.enums.StateHotel;
import next.youbooking.yb.models.enums.StatusRoom;
import next.youbooking.yb.models.vo.AddressVo;
import next.youbooking.yb.models.vo.BedRoomVo;
import next.youbooking.yb.models.vo.HotelVo;
import next.youbooking.yb.repository.HotelRep;
import next.youbooking.yb.security.models.entity.User;
import next.youbooking.yb.security.service.UserDetailsServiceImpl;
import next.youbooking.yb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private BedRoomService bedRoomService;

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
    public Hotel findByUuid(UUID uuid) {
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
    @Transactional
    public Hotel saveHotel(HotelVo hotel, String username) {

        Hotel saveHotel = new Hotel();
        User user = this.checkUser(username);
        saveHotel.setUser(user);

        Address address = this.getAddressFromHotel(hotel.getAddress());

        saveHotel.setAddress(address);
        saveHotel.setUuid(UUID.randomUUID());
        saveHotel.setName(hotel.getName());
        saveHotel.setDescription(hotel.getDescription());
        saveHotel.setStateHotel(StateHotel.OPEN);

        saveHotel = this.hotelRep.save(saveHotel);
        Hotel finalSaveHotel = saveHotel;
        hotel.getAttachments().forEach(attachmentVo -> {
            try {
                Attachment attachment = this.attachmentService.saveAtt(attachmentVo.getTitle(), attachmentVo.getDescription(), finalSaveHotel.getUuid());
                finalSaveHotel.getAttachments().add(attachment);
            }catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new ServiceUnavailableException(e.getMessage());
            }
        });

        ArrayList<BedRoom> bedRooms = getBedRoom(hotel.getBedRooms());
        bedRooms.forEach(bedRoom -> {
            try {
                BedRoom room = this.bedRoomService.saveBedRoom(bedRoom, finalSaveHotel.getUuid());
                finalSaveHotel.getBedRooms().add(room);
            }catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new ServiceUnavailableException(e.getMessage());
            }
        });
        return finalSaveHotel;
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
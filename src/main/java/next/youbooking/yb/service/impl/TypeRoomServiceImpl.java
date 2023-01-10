package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.TypeRoom;
import next.youbooking.yb.repository.TypeRoomRep;
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
public class TypeRoomServiceImpl implements TypeRoomService {
    @Autowired
    TypeRoomRep typeRoomRep;

    @Override
    public TypeRoom findByUuid(String uuid) {
        return typeRoomRep.findByUuid(UUID.fromString(uuid));
    }

    @Override
    public int deleteByUuid(String uuid) {
        return typeRoomRep.deleteByUuid(uuid);
    }

    @Override
    public List<TypeRoom> findAll() {
        return typeRoomRep.findAll();
    }

    @Override
    public Page<TypeRoom> findAll(Pageable pageable) {
        return typeRoomRep.findAll(pageable);
    }

    @Override
    public Page<TypeRoom> findAll(PageRequest pageRequest) {
        return typeRoomRep.findAll(pageRequest);
    }

    @Override
    public TypeRoom save(TypeRoom typeRoom) {
        typeRoom.setUuid(UUID.randomUUID());
        typeRoom.setDescription("description : "+typeRoom.getName());
        return typeRoomRep.save(typeRoom);
    }

    @Override
    public TypeRoom update(TypeRoom typeRome) {
        return null;
    }

    @Override
    public boolean existsByUuid(String uuid) {
        return existsByUuid(uuid);
    }

    @Override
    public boolean existsByName(String name) {
        return existsByName(name);
    }
    @Override
    public void add() {
        List<String> strings = new ArrayList<>();
        strings.add("Standard Double Room");
        strings.add("Standard Single Room");
        strings.add("Triple Room");
        strings.add("Junior Suite");
        strings.add("Superior Double Room");
        strings.add("Family Suite");
        strings.add("Family Room with Sea View");
        strings.add("Presidential Suite");
        strings.forEach(s -> {
            TypeRoom roomType = new TypeRoom();
            roomType.setName(s);
            this.save(roomType);
        });
    }
}
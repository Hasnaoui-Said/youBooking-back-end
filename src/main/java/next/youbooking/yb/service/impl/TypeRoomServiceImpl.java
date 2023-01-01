package next.youbooking.yb.service.impl;

import next.youbooking.yb.models.entity.TypeRoom;
import next.youbooking.yb.repository.TypeRoomRep;
import next.youbooking.yb.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeRoomServiceImpl implements TypeRoomService {
    @Autowired
    TypeRoomRep typeRoomRep;

    @Override
    public TypeRoom findByUuid(String uuid) {
        return typeRoomRep.findByUuid(uuid);
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
    public TypeRoom save(TypeRoom entity) {
        return typeRoomRep.save(entity);
    }
}
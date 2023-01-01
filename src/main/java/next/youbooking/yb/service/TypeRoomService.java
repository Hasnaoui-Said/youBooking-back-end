package next.youbooking.yb.service;

import next.youbooking.yb.models.entity.TypeRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeRoomService {
    TypeRoom findByUuid(String uuid);

    int deleteByUuid(String uuid);

    List<TypeRoom> findAll();

    Page<TypeRoom> findAll(Pageable pageable);

    Page<TypeRoom> findAll(PageRequest pageRequest);

    TypeRoom save(TypeRoom entity);
}

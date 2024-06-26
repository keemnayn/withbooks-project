package kr.withbooks.web.repository;

import kr.withbooks.web.entity.DebateRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DebateRoomRepository {

    DebateRoom findById(Long id, Long withId);
    Long save(DebateRoom debateRoom);
    void deleteAllById(Long withId, List<Long> ids);

}

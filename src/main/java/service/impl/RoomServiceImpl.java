package service.impl;

import dao.CrudRepository;
import entity.hotelAndHouse.Room;
import service.RoomService;

public class RoomServiceImpl extends GenericServiceImpl<Long, Room> implements RoomService {

    public RoomServiceImpl(CrudRepository<Long, Room> genericRepository) {
        super(genericRepository);
    }
}

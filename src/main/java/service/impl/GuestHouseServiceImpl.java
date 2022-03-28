package service.impl;

import dao.CrudRepository;
import entity.hotelAndHouse.GuestHouse;
import service.GuestHouseService;

public class GuestHouseServiceImpl extends GenericServiceImpl<Long, GuestHouse> implements GuestHouseService {

    public GuestHouseServiceImpl(CrudRepository<Long, GuestHouse> genericRepository) {
        super(genericRepository);
    }
}

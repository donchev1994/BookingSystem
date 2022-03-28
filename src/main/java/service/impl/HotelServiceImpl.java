package service.impl;

import dao.CrudRepository;
import entity.hotelAndHouse.Hotel;
import service.HotelService;

public class HotelServiceImpl extends GenericServiceImpl<Long, Hotel> implements HotelService {
    public HotelServiceImpl(CrudRepository<Long, Hotel> genericRepository) {
        super(genericRepository);
    }
}

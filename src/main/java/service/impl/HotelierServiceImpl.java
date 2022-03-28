package service.impl;

import dao.CrudRepository;
import entity.users.Hotelier;
import service.HotelierService;

import java.util.Collection;

public class HotelierServiceImpl extends GenericServiceImpl<Long,Hotelier> implements HotelierService {

    public HotelierServiceImpl(CrudRepository<Long, Hotelier> genericRepository) {
        super(genericRepository);
    }

}

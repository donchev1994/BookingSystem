package service;

import entity.users.AdminHoteliers;

public interface AdminHoteliersService extends GenericService<Long, AdminHoteliers>{
    void getAllHoteliers();
}

package service.impl;


import dao.CrudRepository;
import entity.users.AdminHoteliers;
import service.AdminHoteliersService;


public class AdminHoteliersServiceImpl extends GenericServiceImpl<Long,AdminHoteliers>
        implements AdminHoteliersService {


    public AdminHoteliersServiceImpl(CrudRepository<Long, AdminHoteliers> genericRepository) {
        super(genericRepository);
    }
}

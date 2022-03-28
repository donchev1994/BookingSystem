package service.impl;


import dao.CrudRepository;
import entity.users.AdminRegUsers;
import service.AdminRegUsersService;



public class AdminRegUsersServiceImpl extends GenericServiceImpl<Long,AdminRegUsers>
        implements AdminRegUsersService {


    public AdminRegUsersServiceImpl(CrudRepository<Long, AdminRegUsers> genericRepository) {
        super(genericRepository);
    }
}

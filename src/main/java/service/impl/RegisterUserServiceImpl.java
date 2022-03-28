package service.impl;

import dao.CrudRepository;
import entity.users.RegisteredUser;
import service.RegisterUserService;



public class RegisterUserServiceImpl extends GenericServiceImpl<Long,RegisteredUser>
        implements RegisterUserService {


    public RegisterUserServiceImpl(CrudRepository<Long, RegisteredUser> genericRepository) {
        super(genericRepository);
    }




}

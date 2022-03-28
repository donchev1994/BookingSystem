package service.impl;


import dao.CrudRepository;
import entity.users.Administrator;
import service.AdministratorService;


public class AdministratorServiceImpl extends GenericServiceImpl<Long,Administrator>
        implements AdministratorService {

    public AdministratorServiceImpl(CrudRepository<Long, Administrator> genericRepository) {
        super(genericRepository);
    }
}


package service.impl;


import dao.CrudRepository;
import entity.users.AdminRegUsers;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdminRegUsersService;

import java.util.Collection;


public class AdminRegUsersServiceImpl implements AdminRegUsersService {


    public AdminRegUsersServiceImpl(CrudRepository<Long, AdminRegUsers> genericRepository) {
        ;
    }

    @Override
    public Collection<AdminRegUsers> getAll() throws NonexistentEntityException {
        return null;
    }

    @Override
    public AdminRegUsers save(AdminRegUsers entity) throws InvalidEntityDataException {
        return null;
    }

    @Override
    public AdminRegUsers update(AdminRegUsers entity) {
        return null;
    }

    @Override
    public AdminRegUsers delete(AdminRegUsers entity) {
        return null;
    }

    @Override
    public AdminRegUsers findById(Long id) throws NonexistentEntityException {
        return null;
    }
}

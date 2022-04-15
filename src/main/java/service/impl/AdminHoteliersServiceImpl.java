
package service.impl;


import dao.adminhoteliers.AdminHoteliersDao;
import entity.users.AdminHoteliers;
import entity.users.User;
import exception.NonexistentEntityException;
import service.AdminHoteliersService;

import java.util.Collection;


public class AdminHoteliersServiceImpl implements AdminHoteliersService {

    private AdminHoteliersDao adminHoteliersDao;

    public AdminHoteliersServiceImpl(AdminHoteliersDao adminHoteliersDao) {
        this.adminHoteliersDao = adminHoteliersDao;
    }

    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return adminHoteliersDao.read();
    }

    @Override
    public void save(AdminHoteliers entity)  {
        adminHoteliersDao.create(entity);
    }

    @Override
    public void update(AdminHoteliers entity) {
        adminHoteliersDao.update(entity);
    }

    @Override
    public void delete(AdminHoteliers entity) {
        adminHoteliersDao.delete(entity);
    }


    @Override
    public void getAllHoteliers() {
        adminHoteliersDao.getAllHoteliers();
    }
}

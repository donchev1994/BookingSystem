
package service.impl;


import dao.adminusers.AdminRegUsersDao;
import entity.users.AdminRegUsers;
import entity.users.User;
import exception.NonexistentEntityException;
import service.AdminRegUsersService;

import java.util.Collection;


public class AdminRegUsersServiceImpl implements AdminRegUsersService {


    private AdminRegUsersDao adminRegUsersDao;

    public AdminRegUsersServiceImpl(AdminRegUsersDao adminRegUsersDao) {
        this.adminRegUsersDao = adminRegUsersDao;
    }

    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return adminRegUsersDao.read();
    }

    @Override
    public void save(AdminRegUsers entity)  {
        adminRegUsersDao.create(entity);
    }

    @Override
    public void update(AdminRegUsers entity) {
        adminRegUsersDao.update(entity);
    }

    @Override
    public void delete(AdminRegUsers entity) {
        adminRegUsersDao.delete(entity);
    }


    @Override
    public void viewAllUsers() {
        adminRegUsersDao.viewAllUsers();
    }
}

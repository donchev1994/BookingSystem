package service.impl;


import dao.administrator.AdministratorDao;
import dao.CityRepository;
import dao.impl.CityRepositoryImpl;
import entity.city.City;
import entity.users.Administrator;
import entity.users.User;
import exception.NonexistentEntityException;
import service.AdministratorService;

import java.sql.*;
import java.util.Collection;


public class AdministratorServiceImpl implements AdministratorService {



    private AdministratorDao administratorDao;
    private CityRepository cityRepository = new CityRepositoryImpl();


    public AdministratorServiceImpl(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    @Override
    public void addCity(City city) throws SQLException {
        cityRepository.create(city);
    }


    @Override
    public void updateRole(int role, String username) {
        administratorDao.updateRole(role,username);
    }

    @Override
    public void getAllRoles() {
        administratorDao.getAllRoles();
    }

    @Override
    public void registerUser(User entity) {
        administratorDao.create(entity);
    }

    @Override
    public void removeCity(City city) {
        cityRepository.delete(city);
    }

    @Override
    public boolean deleteUser(String username) {
      return  administratorDao.deleteUser(username);
    }


    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return administratorDao.read();
    }

    @Override
    public void save(Administrator entity) {
        administratorDao.create(entity);
    }

    @Override
    public void update(Administrator entity) {
        administratorDao.update(entity);
    }

    @Override
    public void delete(Administrator entity) {
        administratorDao.delete(entity);
    }


}


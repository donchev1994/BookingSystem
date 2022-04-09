package service.impl;


import dao.AdministratorRepository;
import entity.city.City;
import entity.users.Administrator;
import entity.users.RegisteredUser;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdministratorService;
import util.SqlConnector;

import java.sql.SQLException;
import java.util.Collection;


public class AdministratorServiceImpl implements AdministratorService {

    private AdministratorRepository adminRepo;
    private SqlConnector connector = new SqlConnector();

    public AdministratorServiceImpl(AdministratorRepository adminRepo) {
        this.adminRepo = adminRepo;
    }


    @Override
    public void addCity(City city) {
        try {
            connector.addCity(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRole(int role, String username) {
        if(connector.updateRole(role,username)){
            System.out.println("User role is succesfully update.");
        } else {
            System.out.println("User role is invalid.");
        }
    }

    @Override
    public void getAllRoles() {
        connector.getAllRoles();
    }

    @Override
    public RegisteredUser registerUser(RegisteredUser entity) {
        return connector.registerUser(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail());
    }

    @Override
    public Collection<Administrator> getAll() throws NonexistentEntityException {
        return adminRepo.read();
    }

    @Override
    public Administrator save(Administrator entity) throws InvalidEntityDataException {
        return adminRepo.create(entity);
    }

    @Override
    public Administrator update(Administrator entity) {
        return adminRepo.update(entity);
    }

    @Override
    public Administrator delete(Administrator entity) {
        return adminRepo.delete(entity);
    }

    @Override
    public Administrator findById(Long id) throws NonexistentEntityException {
        return adminRepo.findById(id);
    }
}


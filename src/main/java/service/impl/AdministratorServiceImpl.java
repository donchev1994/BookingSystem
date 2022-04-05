package service.impl;


import dao.AdministratorRepository;
import dao.CityRepository;
import entity.city.City;
import entity.users.Administrator;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdministratorService;

import java.util.Collection;


public class AdministratorServiceImpl implements AdministratorService {

    private AdministratorRepository adminRepo;

    public AdministratorServiceImpl(AdministratorRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public AdministratorServiceImpl(AdministratorRepository adminRepo, CityRepository cityRepo) {
        this.adminRepo = adminRepo;
    }


    @Override
    public void addCity(City city) {
        adminRepo.addCity(city);
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
        return null;
    }

    @Override
    public Administrator delete(Administrator entity) {
        return null;
    }

    @Override
    public Administrator findById(Long id) throws NonexistentEntityException {
        return null;
    }
}


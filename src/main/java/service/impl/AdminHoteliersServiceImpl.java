
package service.impl;


import dao.AdminHoteliersRepository;
import dao.AdministratorRepository;
import dao.CrudRepository;
import entity.users.AdminHoteliers;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdminHoteliersService;
import util.UserValidator;

import java.util.Collection;


public class AdminHoteliersServiceImpl implements AdminHoteliersService {

    private AdminHoteliersRepository adminRepo;
    private final UserValidator userValidator;

    public AdminHoteliersServiceImpl(AdminHoteliersRepository adminRepo) {
        this.adminRepo = adminRepo;
        this.userValidator = new UserValidator();
    }

    public AdminHoteliersServiceImpl(AdminHoteliersRepository adminRepo, UserValidator userValidator) {
        this.adminRepo = adminRepo;
        this.userValidator = userValidator;
    }

    @Override
    public Collection<AdminHoteliers> getAll() throws NonexistentEntityException {
        return null;
    }

    @Override
    public AdminHoteliers save(AdminHoteliers entity) throws InvalidEntityDataException {
        try {
            userValidator.validate(entity);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Error creating admin  %s", entity.getFirstName()), e
            );
        }

        return adminRepo.create(entity);
    }

    @Override
    public AdminHoteliers update(AdminHoteliers entity) {
        return null;
    }

    @Override
    public AdminHoteliers delete(AdminHoteliers entity) {
        return null;
    }

    @Override
    public AdminHoteliers findById(Long id) throws NonexistentEntityException {
        return null;
    }
}

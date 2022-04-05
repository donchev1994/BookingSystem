package service.impl;

import dao.CrudRepository;
import dao.RegisterUserRepository;
import entity.users.RegisteredUser;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.RegisterUserService;
import util.SqlConnector;
import util.UserValidator;

import java.util.Collection;


public class RegisterUserServiceImpl  implements RegisterUserService {

    private RegisterUserRepository registerRepo;
    private final UserValidator userValidator;
    private SqlConnector connector = new SqlConnector();

    public RegisterUserServiceImpl(RegisterUserRepository registerRepo) {
        this.registerRepo = registerRepo;
        this.userValidator = new UserValidator();
    }

    public RegisterUserServiceImpl(RegisterUserRepository registerRepo, UserValidator userValidator){
        this.userValidator = userValidator;
    }

    @Override
    public Collection<RegisteredUser> getAll() throws NonexistentEntityException {
        return registerRepo.read();
    }

    @Override
    public RegisteredUser save(RegisteredUser entity) throws InvalidEntityDataException {
        try {
            userValidator.validate(entity);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Error creating user  '%s'", entity.getFirstName()), e
            );
        }

        return registerRepo.create(entity);
    }

    @Override
    public RegisteredUser update(RegisteredUser entity) {
        return null;
    }

    @Override
    public RegisteredUser delete(RegisteredUser entity) {
        return null;
    }

    @Override
    public RegisteredUser findById(Long id) throws NonexistentEntityException {
        return registerRepo.findById(id);
    }
}

package service.impl;

import dao.CrudRepository;
import entity.users.RegisteredUser;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.RegisterUserService;
import util.UserValidator;


public class RegisterUserServiceImpl extends GenericServiceImpl<Long,RegisteredUser>
        implements RegisterUserService {


    private final UserValidator userValidator;



    public RegisterUserServiceImpl(CrudRepository<Long, RegisteredUser> genericRepository, UserValidator userValidator) {
        super(genericRepository);
        this.userValidator = userValidator;
    }

    public RegisterUserServiceImpl(CrudRepository<Long, RegisteredUser> genericRepository) {
        super(genericRepository);
        this.userValidator = new UserValidator();
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

        return genericRepository.create(entity);
    }
}

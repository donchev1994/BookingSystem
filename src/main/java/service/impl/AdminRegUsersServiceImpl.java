package service.impl;


import dao.CrudRepository;
import entity.users.AdminRegUsers;
import entity.users.RegisteredUser;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.AdminRegUsersService;
import util.UserValidator;


public class AdminRegUsersServiceImpl extends GenericServiceImpl<Long,AdminRegUsers>
        implements AdminRegUsersService {

    private final UserValidator userValidator;


    public AdminRegUsersServiceImpl(CrudRepository<Long, AdminRegUsers> genericRepository) {
        super(genericRepository);
        this.userValidator = new UserValidator();
    }

    public AdminRegUsersServiceImpl(CrudRepository<Long, AdminRegUsers> genericRepository, UserValidator userValidator) {
        super(genericRepository);
        this.userValidator = userValidator;
    }

    @Override
    public AdminRegUsers save(AdminRegUsers entity) throws InvalidEntityDataException {
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

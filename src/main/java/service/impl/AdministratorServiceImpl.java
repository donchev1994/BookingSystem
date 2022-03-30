package service.impl;


import dao.CrudRepository;
import entity.users.AdminRegUsers;
import entity.users.Administrator;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.AdministratorService;
import util.UserValidator;


public class AdministratorServiceImpl extends GenericServiceImpl<Long,Administrator>
        implements AdministratorService {

    private final UserValidator userValidator;


    public AdministratorServiceImpl(CrudRepository<Long, Administrator> genericRepository) {
        super(genericRepository);
        this.userValidator = new UserValidator();
    }

    public AdministratorServiceImpl(CrudRepository<Long, Administrator> genericRepository, UserValidator userValidator) {
        super(genericRepository);
        this.userValidator = userValidator;
    }

    @Override
    public Administrator save(Administrator entity) throws InvalidEntityDataException {
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


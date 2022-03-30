package service.impl;


import dao.AdministratorRepository;
import dao.CrudRepository;
import entity.users.AdminHoteliers;
import entity.users.Administrator;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.AdministratorService;
import util.UserValidator;


public class AdministratorServiceImpl extends GenericServiceImpl<Long,Administrator>
        implements AdministratorService {

    private AdministratorRepository administratorRepository;
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

    @Override
    public AdminHoteliers createAdminHotelier(AdminHoteliers entity) {
        return administratorRepository.createAdminHotelier(entity);
    }


}


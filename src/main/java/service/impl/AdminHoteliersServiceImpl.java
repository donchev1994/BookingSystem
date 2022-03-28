package service.impl;


import dao.CrudRepository;
import entity.users.AdminHoteliers;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.AdminHoteliersService;
import util.UserValidator;


public class AdminHoteliersServiceImpl extends GenericServiceImpl<Long,AdminHoteliers>
        implements AdminHoteliersService {

    private final UserValidator userValidator;

    public AdminHoteliersServiceImpl(CrudRepository<Long, AdminHoteliers> genericRepository) {
        super(genericRepository);
        this.userValidator = new UserValidator();
    }

    public AdminHoteliersServiceImpl(CrudRepository<Long, AdminHoteliers> genericRepository, UserValidator userValidator) {
        super(genericRepository);
        this.userValidator = userValidator;
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

        return genericRepository.create(entity);
    }
}

package service.impl;

import dao.CrudRepository;
import entity.hotelAndHouse.GuestHouse;
import entity.users.AdminRegUsers;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.GuestHouseService;
import util.HotelAndGuestHouseValidator;

public class GuestHouseServiceImpl extends GenericServiceImpl<Long, GuestHouse> implements GuestHouseService {

    private final HotelAndGuestHouseValidator houseValidator;

    public GuestHouseServiceImpl(CrudRepository<Long, GuestHouse> genericRepository) {
        super(genericRepository);
        this.houseValidator = new HotelAndGuestHouseValidator();
    }

    public GuestHouseServiceImpl(CrudRepository<Long, GuestHouse> genericRepository, HotelAndGuestHouseValidator houseValidator) {
        super(genericRepository);
        this.houseValidator = houseValidator;
    }

    @Override
    public GuestHouse save(GuestHouse entity) throws InvalidEntityDataException {
        try {
            houseValidator.validate(entity);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Error creating guest house  '%s'", entity.getName()), e
            );
        }

        return genericRepository.create(entity);
    }
}

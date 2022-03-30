package service.impl;

import dao.CrudRepository;
import entity.hotelAndHouse.Hotel;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import service.HotelService;
import util.HotelAndGuestHouseValidator;

public class HotelServiceImpl extends GenericServiceImpl<Long, Hotel> implements HotelService {

    private final HotelAndGuestHouseValidator houseValidator;


    public HotelServiceImpl(CrudRepository<Long, Hotel> genericRepository) {
        super(genericRepository);
        this.houseValidator = new HotelAndGuestHouseValidator();
    }

    public HotelServiceImpl(CrudRepository<Long, Hotel> genericRepository, HotelAndGuestHouseValidator houseValidator) {
        super(genericRepository);
        this.houseValidator = houseValidator;
    }

    @Override
    public Hotel save(Hotel entity) throws InvalidEntityDataException {
        try {
            houseValidator.validate(entity);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Error creating user  '%s'", entity.getName()), e
            );
        }
        return genericRepository.create(entity);
    }
}

package util;

import entity.city.City;
import entity.hotelAndHouse.Properties;
import exception.ConstraintViolation;
import exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class HotelAndGuestHouseValidator {


    public void validate(Properties properties) throws ConstraintViolationException {
        List<ConstraintViolation> violations = new ArrayList<>();

        if(properties.getName().trim().length() < 2 || properties.getName().trim().length() > 12){
            violations.add(
                    new ConstraintViolation(properties.getClass().getName(), " name", properties.getName(),
                            "Name length should be between 2 and 12 characters.")
            );
        }



        if(properties.getDescription().trim().length() < 20 || properties.getDescription().trim().length() > 350){
            violations.add(
                    new ConstraintViolation(properties.getClass().getName(), " description", properties.getDescription(),
                            "Description length should be between 20 and 350 characters.")
            );
        }

        if(properties.getStars() < 1 || properties.getStars() > 5){
            violations.add(
                    new ConstraintViolation(properties.getClass().getName(), " stars", properties.getStars(),
                            "Stars should be between 1 and 5 stars.")
            );
        }

        if(properties.getAddress().trim().length() < 5 || properties.getAddress().trim().length() > 25){
            violations.add(
                    new ConstraintViolation(properties.getClass().getName(), " address", properties.getAddress(),
                            "Address length should be between 5 and 25 characters")
            );
        }



        if (violations.size() > 0) {
            throw new ConstraintViolationException("Invalid entity field", violations);
        }
    }
}

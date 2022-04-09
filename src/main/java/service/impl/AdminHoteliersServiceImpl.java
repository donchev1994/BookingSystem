
package service.impl;


import dao.UserRepository;
import entity.users.AdminHoteliers;
import entity.users.User;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdminHoteliersService;
import util.UserValidator;

import java.sql.SQLException;
import java.util.Collection;


public class AdminHoteliersServiceImpl implements AdminHoteliersService {

    private UserRepository userRepository;


    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(AdminHoteliers entity) throws InvalidEntityDataException, SQLException {
        userRepository.create(entity);
    }

    @Override
    public void update(AdminHoteliers entity) {
        userRepository.update(entity);
    }

    @Override
    public void delete(AdminHoteliers entity) {
        userRepository.delete(entity);
    }


}

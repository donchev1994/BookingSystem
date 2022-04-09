
package service.impl;


import dao.CrudRepository;
import dao.UserRepository;
import entity.users.AdminRegUsers;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdminRegUsersService;
import util.SqlConnector;

import java.sql.SQLException;
import java.util.Collection;


public class AdminRegUsersServiceImpl implements AdminRegUsersService {

    private UserRepository userRepository;


    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(AdminRegUsers entity) throws InvalidEntityDataException {
        try {
            userRepository.create(entity);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid entity.");
        }
    }

    @Override
    public void update(AdminRegUsers entity) {
        userRepository.update(entity);
    }

    @Override
    public void delete(AdminRegUsers entity) {
        userRepository.delete(entity);
    }


}

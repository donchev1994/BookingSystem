
package service.impl;

import dao.RegisterUserRepository;
import entity.users.RegisteredUser;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.RegisterUserService;
import util.SqlConnector;
import util.UserValidator;


import java.sql.SQLException;
import java.util.Collection;


public class RegisterUserServiceImpl  implements RegisterUserService {

    private RegisterUserRepository registerRepo;
    private final UserValidator userValidator;
    private SqlConnector connector = new SqlConnector();

    public RegisterUserServiceImpl(RegisterUserRepository registerRepo) {
        this.registerRepo = registerRepo;
        this.userValidator = new UserValidator();
    }

    public RegisterUserServiceImpl(RegisterUserRepository registerRepo, UserValidator userValidator){
        this.userValidator = userValidator;
    }

    @Override
    public Collection<RegisteredUser> getAll() throws NonexistentEntityException {
        return registerRepo.read();
    }

    @Override
    public RegisteredUser save(RegisteredUser entity) throws InvalidEntityDataException {
        return connector.registerUser(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail());
    }

    @Override
    public RegisteredUser update(RegisteredUser entity) {
        return registerRepo.update(entity);
    }

    @Override
    public RegisteredUser delete(RegisteredUser entity) {
        return registerRepo.delete(entity);
    }

    @Override
    public RegisteredUser findById(Long id) throws NonexistentEntityException {
        return registerRepo.findById(id);
    }

    @Override
    public String getAllCities() {
        try {
            return connector.getAllCities();
        } catch (SQLException e) {
            return "Dont have added cities";
        }
    }

    @Override
    public String getCityById(Long id) {
        return connector.getCity(id);
    }

    @Override
    public String getAllHotelsByCity(String cityName) {
        return connector.getAllHotels(cityName);
    }

    @Override
    public boolean updatePassword(String username, String password) throws SQLException {
     return connector.updatePassword(username,password);
    }
}

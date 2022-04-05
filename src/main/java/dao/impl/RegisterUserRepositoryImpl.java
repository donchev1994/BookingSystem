package dao.impl;

import dao.RegisterUserRepository;
import entity.city.City;
import entity.users.RegisteredUser;
import util.SqlConnector;

import java.sql.SQLException;


public class RegisterUserRepositoryImpl extends CrudRepositoryImpl<Long, RegisteredUser> implements RegisterUserRepository {

}

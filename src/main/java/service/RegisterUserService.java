package service;


import entity.users.RegisteredUser;
import entity.users.User;

import java.util.Collection;

public interface RegisterUserService {
    Collection<RegisteredUser> getUsers();
    RegisteredUser addUser(RegisteredUser user);
    RegisteredUser updateUser(RegisteredUser user);
    RegisteredUser deleteUser(RegisteredUser user);
    RegisteredUser findById(Long id);
}

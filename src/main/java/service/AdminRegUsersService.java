package service;

import entity.users.AdminRegUsers;

public interface AdminRegUsersService extends GenericService<Long, AdminRegUsers>{
    void viewAllUsers();
}

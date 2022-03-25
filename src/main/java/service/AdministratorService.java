package service;

import entity.users.Administrator;

import java.util.Collection;

public interface AdministratorService {
    Collection<Administrator> getAdmin();

    Administrator addAdmin(Administrator admin);

    Administrator updateAdmin(Administrator admin);

    Administrator deleteAdmin(Administrator admin);
}

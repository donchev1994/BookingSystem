package service.impl;

import dao.AdministratorRepository;
import entity.users.Administrator;

import java.util.Collection;

public class AdministratorServiceImpl implements service.AdministratorService {
    AdministratorRepository adminRepo;

    public AdministratorServiceImpl(AdministratorRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Collection<Administrator> getAdmin() {
        return adminRepo.read();
    }

    @Override
    public Administrator addAdmin(Administrator admin) {
        return adminRepo.create(admin);
    }

    @Override
    public Administrator updateAdmin(Administrator admin) {
        return adminRepo.update(admin);
    }

    @Override
    public Administrator deleteAdmin(Administrator admin) {
        return adminRepo.delete(admin);
    }
}

package dao.impl;

import dao.AdministratorRepository;
import entity.users.AdminHoteliers;
import entity.users.AdminRegUsers;
import entity.users.Administrator;

import java.util.HashMap;
import java.util.Map;

public class AdministratorRepositoryImpl extends CrudRepositoryImpl<Long,Administrator> implements AdministratorRepository {

    Map<Long,AdminRegUsers> adminRegUsers = new HashMap<>();
    Map<Long,AdminHoteliers> adminHoteliers = new HashMap<>();

    @Override
    public AdminHoteliers createAdminHotelier(AdminHoteliers entity) {
        return adminHoteliers.put(entity.getId(),entity);
    }


    @Override
    public AdminRegUsers createAdminUsers(AdminRegUsers entity) {
        return adminRegUsers.put(entity.getId(),entity);
    }

    @Override
    public void updateAdminHotelier(AdminHoteliers entity, String email) {
        entity.setEmail(email);
    }
}

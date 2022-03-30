package dao;

import entity.users.AdminHoteliers;
import entity.users.AdminRegUsers;
import entity.users.Administrator;

public interface AdministratorRepository extends CrudRepository<Long, Administrator> {


    AdminHoteliers createAdminHotelier(AdminHoteliers entity);

    AdminRegUsers createAdminUsers(AdminRegUsers entity);

    void updateAdminHotelier(AdminHoteliers entity, String email);
}

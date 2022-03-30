package service;

import entity.users.AdminHoteliers;
import entity.users.Administrator;

import java.util.Collection;

public interface AdministratorService extends GenericService<Long,Administrator> {
    AdminHoteliers createAdminHotelier(AdminHoteliers entity);
}

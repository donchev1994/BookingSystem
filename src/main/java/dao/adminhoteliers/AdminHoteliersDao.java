package dao.adminhoteliers;

import dao.UserRepository;

public interface AdminHoteliersDao extends UserRepository {
    void getAllHoteliers();
}

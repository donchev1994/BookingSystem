package dao.registeruser;


import dao.UserRepository;
import entity.users.User;


public interface RegisterUserDao extends UserRepository {

    String getAllCities() ;
    String getCityById(Long id);
    String getAllHotelsByCity(String cityName);
    boolean updatePassword(User user);
    void getTypeAndPriceForRoom(String houseName);
    boolean writeComment(int houseId,int userId);
    int getIdByUsername(String username);
    int getLastCommentId(String date);

}

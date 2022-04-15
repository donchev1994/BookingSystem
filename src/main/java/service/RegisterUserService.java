
package service;


import entity.Comments.Comment;
import entity.city.City;
import entity.users.RegisteredUser;
import entity.users.User;
import exception.NonexistentEntityException;

import java.sql.SQLException;
import java.util.Collection;

public interface RegisterUserService extends GenericService<Long, RegisteredUser> {

    String getAllCities();
    String getCityById(Long id);
    String getAllHotelsByCity(String cityName);
    boolean updatePassword(User user);
    void getTypeAndPriceForRoom(String houseName);
    boolean writeComment(int houseId, int userId);
    void addComment(Comment comment);

    int getId(String username);
    int getLastCommentId(String date);
}


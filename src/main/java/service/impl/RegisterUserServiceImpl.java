
package service.impl;

import dao.CommentRepository;
import dao.impl.CommentRepositoryImpl;
import dao.registeruser.RegisterUserDao;
import entity.Comments.Comment;
import entity.users.RegisteredUser;
import entity.users.User;

import exception.NonexistentEntityException;
import service.RegisterUserService;


import java.util.Collection;


public class RegisterUserServiceImpl implements RegisterUserService {


    private RegisterUserDao registerUserDao;
    private CommentRepository commentRepository = new CommentRepositoryImpl();

    public RegisterUserServiceImpl(RegisterUserDao userRepository) {
        this.registerUserDao = userRepository;
    }


    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return registerUserDao.read();
    }

    @Override
    public void save(RegisteredUser entity) {
        registerUserDao.create(entity);
    }


    @Override
    public void update(RegisteredUser entity) {
        registerUserDao.update(entity);
    }


    @Override
    public void delete(RegisteredUser entity) {
        registerUserDao.delete(entity);
    }


    @Override
    public String getAllCities() {
        return registerUserDao.getAllCities();
    }

    @Override
    public String getCityById(Long id) {
        return registerUserDao.getCityById(id);
    }

    @Override
    public String getAllHotelsByCity(String cityName) {
        return registerUserDao.getAllHotelsByCity(cityName);
    }

    @Override
    public boolean updatePassword(User user) {
        return registerUserDao.update(user);
    }

    @Override
    public void getTypeAndPriceForRoom(String houseName) {

    }

    @Override
    public void addComment(Comment comment){
        commentRepository.create(comment);
    }

    @Override
    public boolean writeComment(int houseId, int commentID) {
        return registerUserDao.writeComment(houseId, commentID);
    }

    @Override
    public int getId(String username) {
        return registerUserDao.getIdByUsername(username);
    }

    @Override
    public int getLastCommentId(String date) {
        return registerUserDao.getLastCommentId(date);
    }
}

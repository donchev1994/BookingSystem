package dao.impl;

import dao.UserRepository;
import entity.enums.Role;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.rules.ExpectedException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    private  static UserRepository userDao = new UserRepositoryImpl();


    @BeforeAll
    static void init(){
        Connection  connection = DatabaseConnection.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userDao = new UserRepositoryImpl(connection);
    }

    @AfterAll
    static void teardown() {
        Connection conn = DatabaseConnection.getConnection();
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void createUserTest()  {
        User user = new User("Ivan","Georgiev"
                ,"vankata944","Donchev94!!","vanko@abv.bg");
        userDao.create(user);
        User userFromDB = userDao.getUserByUsername("vankata944");
        assertEquals(userFromDB.getUsername(),user.getUsername());
    }

    @Test
    void createUserExceptionTest()  {
        User user = new User();
        Throwable exception = assertThrows(EntityPersistenceException.class, () -> userDao.create(user));
        assertEquals("Error creating user",exception.getMessage());
    }



    @Test
    void updateUserTest(){
        User user = new User("Ivan","Georgiev"
                ,"vankata944","Donchev94!!","vanko@abv.bg");
        userDao.create(user);
        user.setPassword("Georgi944!");
        userDao.update(user);
        User userFromDb = userDao.getUserByUsername("vankata944");
        assertEquals("Georgi944!", userFromDb.getPassword());
    }


    @Test
    void deleteUserTest(){
        User user = new User("Ivan","Georgiev"
                ,"vankata944","Donchev94!!","vanko@abv.bg");
        userDao.delete(user);
        User userFromDb = userDao.getUserByUsername("vankata944");
        assertEquals(userFromDb.getUsername(), null);
    }

    @Test
    void readUser() throws NonexistentEntityException {
        Collection<User> users = userDao.read();
        for (User user : users) {
            if(user.getUsername().equals("yasen92")){
                assertEquals(user.getUsername(),"yasen92");
            }
        }
    }
}
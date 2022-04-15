package dao.administrator;

import entity.enums.Role;
import entity.users.User;
import exception.NonexistentEntityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;


class AdministratorDaoImplTest {

    private static User user;
    private static AdministratorDao adminDao;

    @BeforeAll
    static void setUp(){
       user = new User("Ivan","Ivanov","vankata94","Vankata94!","ivan@abv.bg",
                LocalDate.now(), Role.USER);
    }

    @BeforeAll
    static void test(){
        Connection connection = DatabaseConnection.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adminDao = new AdministratorDaoImpl(connection);
    }

    @Test
    public void testIt() throws NonexistentEntityException {

       adminDao.deleteUser("vankata94");
       Collection<User> userFromDb = adminDao.read();

    }
}
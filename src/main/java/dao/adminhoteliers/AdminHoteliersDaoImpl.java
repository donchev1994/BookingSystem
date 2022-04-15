package dao.adminhoteliers;

import dao.impl.UserRepositoryImpl;
import exception.EntityPersistenceException;
import util.DatabaseConnection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;




public class AdminHoteliersDaoImpl extends UserRepositoryImpl implements AdminHoteliersDao {
    private static final String GET_ALL_HOTELIERS = "SELECT * FROM users WHERE role_id = 4";

    Connection connection = DatabaseConnection.getConnection();

    @Override
    public void getAllHoteliers() {
        StringBuilder stringBuilder = new StringBuilder();
        try(var stmt = connection.prepareStatement(GET_ALL_HOTELIERS)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                stringBuilder.append("ID: ").append(rs.getInt(1)).append(" | ").append(rs.getString("first_name")).append(" | ")
                        .append(rs.getString("last_name")).append(" | username: ").append(rs.getString("username")).append(System.lineSeparator());
            }
            System.out.println(stringBuilder);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid command.");
        }
    }
}

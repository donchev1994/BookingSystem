package dao.registeruser;

import dao.impl.UserRepositoryImpl;
import entity.Comments.Comment;
import entity.users.User;
import exception.EntityPersistenceException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterUserDaoImpl extends UserRepositoryImpl implements RegisterUserDao {
    private static final String GET_ALL_HOTELS = "SELECT * FROM houses JOIN cities AS c WHERE c.name = ?;";
    private static final String GET_ALL_CITIES = "SELECT id,name FROM cities";
    private static final String GET_CITY = "SELECT * FROM cities WHERE id = ?;";
    private static final String SELECT_ROOMS = "SELECT r.id,r.type_of_room,r.price_per_day FROM houses h " +
            "JOIN rooms r on h.id = r.houses_id " +
            "WHERE h.name= ?;";
    private static final String GET_ID_BY_USERNAME = "SELECT id FROM users WHERE username=?;";
    private static final String WRITE_COMMENT = "UPDATE comments SET houses_id=? WHERE id=?;";
    private static final String GET_ID_COMMENT = "SELECT id FROM comments WHERE description=?;";

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(User entity) {
        super.create(entity);
    }

    @Override
    public String getAllCities() {
        StringBuilder sb = new StringBuilder();
        try (var statement = connection.prepareStatement(GET_ALL_CITIES)) {
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()){
                throw new EntityPersistenceException("Invalid ID.");
            }

            while (resultSet.next()) {
                sb.append(resultSet.getString("id"));
                sb.append(". ");
                sb.append(resultSet.getString("name"));
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid ID.");
        }

    }

    @Override
    public String getCityById(Long id) {
        StringBuilder sb = new StringBuilder();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_CITY);
            statement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = statement.executeQuery();



            while (resultSet.next()) {
                    sb.append("You chose city with id '").append(id).append("'")
                            .append(System.lineSeparator());
                    sb.append(resultSet.getString("name"))
                            .append(System.lineSeparator());
                    sb.append("Description:")
                            .append(System.lineSeparator());
                    sb.append(resultSet.getString("description"));
                }

            if(sb.isEmpty()){
                throw new EntityPersistenceException("Invalid ID:");
            }

            return sb.toString();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid command: ");
        }


    }

    @Override
    public String getAllHotelsByCity(String cityName) {
        StringBuilder sb = new StringBuilder();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_HOTELS);
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sb.append(resultSet.getInt("id")).append(" | ");
                sb.append("Name - ");
                sb.append(resultSet.getString("name"));
                sb.append(" | ");
                sb.append(System.lineSeparator());
                sb.append("Description - ");
                sb.append(resultSet.getString("description"));
                sb.append(" | ");
                sb.append(System.lineSeparator());
                sb.append("Address - ");
                sb.append(resultSet.getString("address"));
                sb.append(" | ");
                sb.append(System.lineSeparator());
                sb.append("---------------------");
                sb.append(System.lineSeparator());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sb.toString();

    }

    @Override
    public boolean updatePassword(User user) {
        return false;
    }

    @Override
    public void getTypeAndPriceForRoom(String houseName) {
        StringBuilder sb = new StringBuilder();
        try (var stmt = connection.prepareStatement(SELECT_ROOMS)) {
            stmt.setString(1, houseName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append(rs.getInt(1)).append(".  ")
                        .append(rs.getString(2).toLowerCase())
                        .append(" room")
                        .append(" - ")
                        .append(rs.getDouble(3))
                        .append("lv.")
                        .append(System.lineSeparator());
            }

            if(sb.isEmpty()){
                throw new EntityPersistenceException("Invalid house name:");
            }

            System.out.println(sb);

        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid house name.");
        }
    }

    @Override
    public int getIdByUsername(String username){
        int id = 0;
        try(var stmt = connection.prepareStatement(GET_ID_BY_USERNAME)) {
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid username.");
        }
    }

    @Override
    public int getLastCommentId(String date) {
        int id = 0;
        try(var stmt = connection.prepareStatement(GET_ID_COMMENT)) {
            stmt.setString(1,date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error.");
        }
    }

    @Override
    public boolean writeComment(int houseId,int commentID) {
        try(var stmt = connection.prepareStatement(WRITE_COMMENT)) {
            stmt.setInt(2,commentID);
            stmt.setInt(1,houseId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid properties.");
        }
    }
}


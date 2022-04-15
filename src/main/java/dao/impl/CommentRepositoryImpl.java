package dao.impl;

import dao.CommentRepository;
import entity.Comments.Comment;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class CommentRepositoryImpl implements CommentRepository {

    private static final String CREATE_COMMENT = "INSERT INTO comments(description, published_date,  user_id) VALUES (?,?,?);";
    private static final String GET_ALL_COMMENT = "SELECT * FROM comments";
    private static final String UPDATE_COMMENT = "UPDATE comments SET description=?;";
    private static final String DELETE_COMMENT = "DELETE FROM comments WHERE id=?;";

    static Connection connection = DatabaseConnection.getConnection();



    @Override
    public void create(Comment entity)  {
        try(var stmt = connection.prepareStatement(CREATE_COMMENT)){
            stmt.setString(1,entity.getDescription());
            stmt.setString(2,String.valueOf(LocalDateTime.now()));
            stmt.setInt(3, entity.getUser_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Comment> read() throws NonexistentEntityException {
        try(var stmt = connection.prepareStatement(GET_ALL_COMMENT)){
            var rs = stmt.executeQuery();
            Collection<Comment> result = new ArrayList<>();
            while (rs.next()){
                result.add(new Comment(
                        rs.getString(1)
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error executing SQL query: " + GET_ALL_COMMENT,e);
        }
    }

    @Override
    public boolean update(Comment entity) {
        try(var stmt = connection.prepareStatement(UPDATE_COMMENT)) {
            stmt.setString(1,entity.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid description");
        }
        return false;
    }

    @Override
    public void delete(Comment entity) {
        try(var stmt = connection.prepareStatement(DELETE_COMMENT)) {
            stmt.setInt(1, Math.toIntExact(entity.getId()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid entity id.");
        }
    }
}

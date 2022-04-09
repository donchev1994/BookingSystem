package dao;


import entity.users.RegisteredUser;
import exception.NonexistentEntityException;

import java.sql.SQLException;
import java.util.Collection;

public interface CrudRepository<K, V extends Identifiable<K>>{
    void create(V entity) throws SQLException;
    Collection<V> read() throws NonexistentEntityException;
    void update(V entity);
    void delete(V entity);

}

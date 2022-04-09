package service;

import entity.users.User;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;

import java.sql.SQLException;
import java.util.Collection;

public interface GenericService<K,V> {
    Collection<User> getAll() throws NonexistentEntityException;

    void save(V entity) throws InvalidEntityDataException, SQLException;

    void update(V entity);

    void delete(V entity);

}

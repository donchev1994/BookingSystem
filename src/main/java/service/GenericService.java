package service;

import entity.users.Administrator;
import exception.NonexistentEntityException;

import java.util.Collection;

public interface GenericService<K,V> {
    Collection<V> getAll() throws NonexistentEntityException;

    V save(V entity);

    V update(V entity);

    V delete(V entity);

    V findById(K id) throws NonexistentEntityException;
}

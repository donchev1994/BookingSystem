package dao;


import exception.NonexistentEntityException;

import java.util.Collection;

public interface CrudRepository<K, V extends Identifiable<K>>{
    V create(V entity);
    Collection<V> read() throws NonexistentEntityException;
    V update(V entity);
    V delete(V entity);
    V findById(K id) throws NonexistentEntityException;

}

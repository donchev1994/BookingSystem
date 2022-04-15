package dao;


import exception.NonexistentEntityException;

import java.util.Collection;

public interface CrudRepository<K, V extends Identifiable<K>>{
    void create(V entity) ;
    Collection<V> read() throws NonexistentEntityException;
    boolean update(V entity);
    void delete(V entity);

}

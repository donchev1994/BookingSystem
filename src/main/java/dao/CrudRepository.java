package dao;


import java.util.Collection;

public interface CrudRepository<K, V extends Identifiable<K>>{
    V create(V entity);
    Collection<V> read();
    V update(V entity);
    V delete(V entity);
    V findById(K id);

}

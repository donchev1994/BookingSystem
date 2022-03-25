package dao.impl;

import dao.CrudRepository;
import dao.Identifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CrudRepositoryImpl<K, V extends Identifiable<K>> implements CrudRepository<K, V> {

    private Map<K, V> entities = new HashMap<>();


    @Override
    public V create(V entity) {
        entity.setId(entity.getId());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Collection<V> read() {
        return entities.values();
    }

    @Override
    public V update(V entity) {
        V old = findById(entity.getId());
        if (old == null) {
            throw new IllegalStateException("its null");
        }
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V delete(V entity) {
        V old = entities.remove(entity.getId());
        if (old == null) {
            throw new IllegalStateException("its null");
        }
        return old;
    }

    @Override
    public V findById(K id) {
        return entities.get(id);
    }
}

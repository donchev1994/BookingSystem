package service.impl;

import dao.CrudRepository;
import dao.Identifiable;
import entity.AbstractEntity;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import lombok.AllArgsConstructor;
import service.GenericService;

import java.util.Collection;


@AllArgsConstructor
public class GenericServiceImpl<K,V extends Identifiable<K>> implements GenericService<K,V> {


    protected CrudRepository<K,V> genericRepository;


    @Override
    public Collection<V> getAll() throws NonexistentEntityException {
        try{
            return genericRepository.read();
        } catch (NonexistentEntityException ex) {
            throw new NonexistentEntityException("There is no such collection.");
        }

    }

    @Override
    public V save(V entity) throws InvalidEntityDataException {
        return genericRepository.create(entity);
    }

    @Override
    public V update(V entity) {

        return genericRepository.update(entity);
    }

    @Override
    public V delete(V entity) {
        return genericRepository.delete(entity);
    }

    @Override
    public V findById(K id) throws NonexistentEntityException {
        try{
            return genericRepository.findById(id);
        } catch (NonexistentEntityException ex){
            throw new NonexistentEntityException("Entity with ID='" + id + "' does not exist.");
        }
    }
}

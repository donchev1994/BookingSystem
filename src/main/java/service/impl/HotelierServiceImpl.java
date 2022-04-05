package service.impl;

import dao.CrudRepository;
import entity.users.Hotelier;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.HotelierService;

import java.util.Collection;

public class HotelierServiceImpl implements HotelierService {



    @Override
    public Collection<Hotelier> getAll() throws NonexistentEntityException {
        return null;
    }

    @Override
    public Hotelier save(Hotelier entity) throws InvalidEntityDataException {
        return null;
    }

    @Override
    public Hotelier update(Hotelier entity) {
        return null;
    }

    @Override
    public Hotelier delete(Hotelier entity) {
        return null;
    }

    @Override
    public Hotelier findById(Long id) throws NonexistentEntityException {
        return null;
    }
}

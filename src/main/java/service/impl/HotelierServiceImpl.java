package service.impl;

import dao.hotelier.HotelierDao;
import dao.HousesRepository;
import dao.RoomRepository;
import dao.impl.HousesRepositoryImpl;
import dao.impl.RoomRepositoryImpl;
import dao.registeruser.RegisterUserDao;
import dao.registeruser.RegisterUserDaoImpl;
import entity.hotelAndHouse.HousesProperties;
import entity.hotelAndHouse.Room;
import entity.users.Hotelier;
import entity.users.User;
import exception.NonexistentEntityException;
import service.HotelierService;

import java.util.Collection;

public class HotelierServiceImpl implements HotelierService {


    private HotelierDao hotelierDao;
    private HousesRepository housesRepository = new HousesRepositoryImpl();
    private RegisterUserDao registerDao = new RegisterUserDaoImpl();
    private RoomRepository roomRepository = new RoomRepositoryImpl();

    public HotelierServiceImpl(HotelierDao hotelierDao) {
        this.hotelierDao = hotelierDao;
    }

    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return hotelierDao.read();
    }

    @Override
    public void save(Hotelier entity) {
        hotelierDao.create(entity);
    }

    @Override
    public void update(Hotelier entity) {
        hotelierDao.update(entity);
    }

    @Override
    public void delete(Hotelier entity) {
        hotelierDao.delete(entity);
    }


    @Override
    public void addHotelOrGuestHouse(HousesProperties house, int propertyId,int cityId) {
        hotelierDao.addHotelOrGuestHouse(house,propertyId,cityId);
    }

    @Override
    public void selectPropertyId() {
        hotelierDao.selectPropertyId();
    }

    @Override
    public void addRoomsToHotel(Room room) {
        roomRepository.create(room);
    }

    @Override
    public void selectCities() {
        hotelierDao.selectCities();
    }

    @Override
    public void getCities(){
        registerDao.getAllCities();
    }


}


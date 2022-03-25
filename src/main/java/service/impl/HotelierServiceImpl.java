package service.impl;

import dao.HotelierRepository;
import entity.users.Hotelier;
import service.HotelierService;

import java.util.Collection;

public class HotelierServiceImpl implements HotelierService {
    HotelierRepository hotelierRepository;

    public HotelierServiceImpl(HotelierRepository hotelierRepository) {
        this.hotelierRepository = hotelierRepository;
    }

    @Override
    public Collection<Hotelier> getHotelier() {
        return hotelierRepository.read();
    }

    @Override
    public Hotelier addHotelier(Hotelier hotelier) {
        return hotelierRepository.create(hotelier);
    }

    @Override
    public Hotelier updateHotelier(Hotelier hotelier) {
        return hotelierRepository.update(hotelier);
    }

    @Override
    public Hotelier deleteHotelier(Hotelier hotelier) {
        return hotelierRepository.delete(hotelier);
    }
}

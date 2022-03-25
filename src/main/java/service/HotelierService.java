package service;

import entity.users.Hotelier;
import entity.users.User;

import java.util.Collection;

public interface HotelierService {
    Collection<Hotelier> getHotelier();
    Hotelier addHotelier(Hotelier hotelier);
    Hotelier updateHotelier(Hotelier hotelier);
    Hotelier deleteHotelier(Hotelier hotelier);
}

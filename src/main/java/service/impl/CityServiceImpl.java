package service.impl;


import dao.CrudRepository;
import entity.city.City;
import service.CityService;



public class CityServiceImpl extends GenericServiceImpl<Long,City> implements CityService {

    public CityServiceImpl(CrudRepository<Long, City> genericRepository) {
        super(genericRepository);
    }
}

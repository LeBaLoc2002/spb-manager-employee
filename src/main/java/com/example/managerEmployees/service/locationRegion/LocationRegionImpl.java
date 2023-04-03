package com.example.managerEmployees.service.locationRegion;

import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocationRegionImpl implements ILocationRegionService{
    @Autowired
    private LocationRegionRepository locationRegionRepository;
    @Override
    public List<LocationRegion> findAll() {
        return null;
    }

    @Override
    public LocationRegion getById(Long id) {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return locationRegionRepository.findById(id);
    }

    @Override
    public LocationRegion getById(String id) {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(String id) {
        return Optional.empty();
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRepository.save(locationRegion);
    }

    @Override
    public void remove(Long id) {

    }
}

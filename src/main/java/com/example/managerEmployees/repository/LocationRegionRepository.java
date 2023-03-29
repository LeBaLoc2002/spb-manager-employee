package com.example.managerEmployees.repository;

import com.example.managerEmployees.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion , Long> {

}

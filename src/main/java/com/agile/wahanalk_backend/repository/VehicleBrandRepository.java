package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.VehicleBrand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBrandRepository extends MongoRepository<VehicleBrand, String> {
    VehicleBrand findByBrandName(String brandName); // Custom query to find by brand name
}

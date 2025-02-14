package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.VehicleCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCategoryRepository extends MongoRepository<VehicleCategory, String> {
    VehicleCategory findByCategoryName(String categoryName); // Custom query to find by category name
}

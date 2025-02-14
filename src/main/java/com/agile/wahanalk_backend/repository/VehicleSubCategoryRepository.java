package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.VehicleSubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleSubCategoryRepository extends MongoRepository<VehicleSubCategory, String> {
    VehicleSubCategory findBySubCategoryName(String subCategoryName); // Custom query to find by subcategory name
}

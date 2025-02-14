package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.VehicleModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleModelRepository extends MongoRepository<VehicleModel, String> {
    List<VehicleModel> findByBrandId(String brandId); // Custom query to find models by brandId
}

package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.VehicleCondition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleConditionRepository extends MongoRepository<VehicleCondition, String> {
    VehicleCondition findByConditionName(String conditionName); // Custom query to find by condition name
}

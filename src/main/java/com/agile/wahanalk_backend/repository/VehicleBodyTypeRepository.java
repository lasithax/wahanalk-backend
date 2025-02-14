package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.VehicleBodyType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBodyTypeRepository extends MongoRepository<VehicleBodyType, String> {

}

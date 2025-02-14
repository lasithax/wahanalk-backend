package com.agile.wahanalk_backend.repository;

import com.agile.wahanalk_backend.model.Advertisement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends MongoRepository<Advertisement, String> {
    List<Advertisement> findByUserId(String userId); // Custom query to find advertisements by user ID
    List<Advertisement> findByCategoryId(String categoryId); // Find by category
}

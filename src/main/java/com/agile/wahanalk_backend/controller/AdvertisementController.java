package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.Advertisement;
import com.agile.wahanalk_backend.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/advertisements")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    // Get all advertisements
    @GetMapping("/all")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements() {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        return ResponseEntity.ok(advertisements);
    }

    // Get a specific advertisement by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String id) {
        return advertisementRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get advertisements by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Advertisement>> getAdvertisementsByUserId(@PathVariable String userId) {
        List<Advertisement> advertisements = advertisementRepository.findByUserId(userId);
        return !advertisements.isEmpty() ? ResponseEntity.ok(advertisements) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get advertisements by category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Advertisement>> getAdvertisementsByCategoryId(@PathVariable String categoryId) {
        List<Advertisement> advertisements = advertisementRepository.findByCategoryId(categoryId);
        return !advertisements.isEmpty() ? ResponseEntity.ok(advertisements) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add a new advertisement
    @PostMapping("/add")
    public ResponseEntity<Advertisement> createAdvertisement(@RequestBody Advertisement advertisement) {
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdvertisement);
    }

    // Update an existing advertisement
    @PutMapping("/{id}")
    public ResponseEntity<Advertisement> updateAdvertisement(@PathVariable String id, @RequestBody Advertisement updatedAdvertisement) {
        return advertisementRepository.findById(id)
                .map(advertisement -> {
                    advertisement.setUserId(updatedAdvertisement.getUserId());
                    advertisement.setCategoryId(updatedAdvertisement.getCategoryId());
                    advertisement.setSubCategoryId(updatedAdvertisement.getSubCategoryId());
                    advertisement.setConditionId(updatedAdvertisement.getConditionId());
                    advertisement.setBrandId(updatedAdvertisement.getBrandId());
                    advertisement.setModelId(updatedAdvertisement.getModelId());
                    advertisement.setTrimEdition(updatedAdvertisement.getTrimEdition());
                    advertisement.setYearManufacture(updatedAdvertisement.getYearManufacture());
                    advertisement.setMileage(updatedAdvertisement.getMileage());
                    advertisement.setFuelTypeId(updatedAdvertisement.getFuelTypeId());
                    advertisement.setTransmissionTypeId(updatedAdvertisement.getTransmissionTypeId());
                    advertisement.setBodyTypeId(updatedAdvertisement.getBodyTypeId());
                    advertisement.setDescription(updatedAdvertisement.getDescription());
                    advertisement.setPrice(updatedAdvertisement.getPrice());
                    advertisement.setAdvertisementDate(updatedAdvertisement.getAdvertisementDate());

                    advertisementRepository.save(advertisement);
                    return ResponseEntity.ok(advertisement);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete an advertisement
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdvertisement(@PathVariable String id) {
        if (advertisementRepository.existsById(id)) {
            advertisementRepository.deleteById(id);
            return ResponseEntity.ok("Advertisement with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Advertisement not found with ID " + id);
        }
    }
}

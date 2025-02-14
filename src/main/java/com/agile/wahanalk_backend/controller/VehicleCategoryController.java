package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.VehicleCategory;
import com.agile.wahanalk_backend.repository.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/vehicle-categories")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class VehicleCategoryController {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    // Get all vehicle categories
    @GetMapping("/all")
    public ResponseEntity<List<VehicleCategory>> getAllVehicleCategories() {
        List<VehicleCategory> categories = vehicleCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    // Get a specific vehicle category by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehicleCategory> getVehicleCategoryById(@PathVariable String id) {
        return vehicleCategoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get a vehicle category by name
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<VehicleCategory> getVehicleCategoryByName(@PathVariable String categoryName) {
        VehicleCategory category = vehicleCategoryRepository.findByCategoryName(categoryName);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add a new vehicle category
    @PostMapping("/add")
    public ResponseEntity<VehicleCategory> createVehicleCategory(@RequestBody VehicleCategory vehicleCategory) {
        VehicleCategory savedCategory = vehicleCategoryRepository.save(vehicleCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update an existing vehicle category
    @PutMapping("/{id}")
    public ResponseEntity<VehicleCategory> updateVehicleCategory(@PathVariable String id, @RequestBody VehicleCategory updatedCategory) {
        return vehicleCategoryRepository.findById(id)
                .map(category -> {
                    category.setCategoryName(updatedCategory.getCategoryName());
                    vehicleCategoryRepository.save(category);
                    return ResponseEntity.ok(category);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a vehicle category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleCategory(@PathVariable String id) {
        if (vehicleCategoryRepository.existsById(id)) {
            vehicleCategoryRepository.deleteById(id);
            return ResponseEntity.ok("Vehicle category with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle category not found with ID " + id);
        }
    }
}

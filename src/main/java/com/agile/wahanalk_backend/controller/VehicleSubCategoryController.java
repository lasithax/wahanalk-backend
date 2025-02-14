package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.VehicleSubCategory;
import com.agile.wahanalk_backend.repository.VehicleSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-subcategories")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class VehicleSubCategoryController {

    @Autowired
    private VehicleSubCategoryRepository vehicleSubCategoryRepository;

    // Get all vehicle subcategories
    @GetMapping("/all")
    public ResponseEntity<List<VehicleSubCategory>> getAllVehicleSubCategories() {
        List<VehicleSubCategory> subCategories = vehicleSubCategoryRepository.findAll();
        return ResponseEntity.ok(subCategories);
    }

    // Get a specific vehicle subcategory by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehicleSubCategory> getVehicleSubCategoryById(@PathVariable String id) {
        return vehicleSubCategoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get a vehicle subcategory by name
    @GetMapping("/name/{subCategoryName}")
    public ResponseEntity<VehicleSubCategory> getVehicleSubCategoryByName(@PathVariable String subCategoryName) {
        VehicleSubCategory subCategory = vehicleSubCategoryRepository.findBySubCategoryName(subCategoryName);
        return subCategory != null ? ResponseEntity.ok(subCategory) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add a new vehicle subcategory
    @PostMapping("/add")
    public ResponseEntity<VehicleSubCategory> createVehicleSubCategory(@RequestBody VehicleSubCategory vehicleSubCategory) {
        VehicleSubCategory savedSubCategory = vehicleSubCategoryRepository.save(vehicleSubCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubCategory);
    }

    // Update an existing vehicle subcategory
    @PutMapping("/{id}")
    public ResponseEntity<VehicleSubCategory> updateVehicleSubCategory(@PathVariable String id, @RequestBody VehicleSubCategory updatedSubCategory) {
        return vehicleSubCategoryRepository.findById(id)
                .map(subCategory -> {
                    subCategory.setSubCategoryName(updatedSubCategory.getSubCategoryName());
                    vehicleSubCategoryRepository.save(subCategory);
                    return ResponseEntity.ok(subCategory);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a vehicle subcategory
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleSubCategory(@PathVariable String id) {
        if (vehicleSubCategoryRepository.existsById(id)) {
            vehicleSubCategoryRepository.deleteById(id);
            return ResponseEntity.ok("Vehicle subcategory with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle subcategory not found with ID " + id);
        }
    }
}

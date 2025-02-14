package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.VehicleBrand;
import com.agile.wahanalk_backend.repository.VehicleBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-brands")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class VehicleBrandController {

    @Autowired
    private VehicleBrandRepository vehicleBrandRepository;

    // Get all vehicle brands
    @GetMapping("/all")
    public ResponseEntity<List<VehicleBrand>> getAllVehicleBrands() {
        List<VehicleBrand> brands = vehicleBrandRepository.findAll();
        return ResponseEntity.ok(brands);
    }

    // Get a specific vehicle brand by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehicleBrand> getVehicleBrandById(@PathVariable String id) {
        return vehicleBrandRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get a vehicle brand by name
    @GetMapping("/name/{brandName}")
    public ResponseEntity<VehicleBrand> getVehicleBrandByName(@PathVariable String brandName) {
        VehicleBrand brand = vehicleBrandRepository.findByBrandName(brandName);
        return brand != null ? ResponseEntity.ok(brand) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add a new vehicle brand
    @PostMapping("/add")
    public ResponseEntity<VehicleBrand> createVehicleBrand(@RequestBody VehicleBrand vehicleBrand) {
        VehicleBrand savedBrand = vehicleBrandRepository.save(vehicleBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
    }

    // Update an existing vehicle brand
    @PutMapping("/{id}")
    public ResponseEntity<VehicleBrand> updateVehicleBrand(@PathVariable String id, @RequestBody VehicleBrand updatedBrand) {
        return vehicleBrandRepository.findById(id)
                .map(brand -> {
                    brand.setBrandName(updatedBrand.getBrandName());
                    vehicleBrandRepository.save(brand);
                    return ResponseEntity.ok(brand);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a vehicle brand
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleBrand(@PathVariable String id) {
        if (vehicleBrandRepository.existsById(id)) {
            vehicleBrandRepository.deleteById(id);
            return ResponseEntity.ok("Vehicle brand with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle brand not found with ID " + id);
        }
    }
}

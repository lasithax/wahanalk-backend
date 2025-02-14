package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.VehicleModel;
import com.agile.wahanalk_backend.repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-models")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class VehicleModelController {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    // Get all vehicle models
    @GetMapping("/all")
    public ResponseEntity<List<VehicleModel>> getAllVehicleModels() {
        List<VehicleModel> models = vehicleModelRepository.findAll();
        return ResponseEntity.ok(models);
    }

    // Get a specific vehicle model by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehicleModel> getVehicleModelById(@PathVariable String id) {
        return vehicleModelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get vehicle models by brand ID
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<VehicleModel>> getVehicleModelsByBrandId(@PathVariable String brandId) {
        List<VehicleModel> models = vehicleModelRepository.findByBrandId(brandId);
        return models.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(models);
    }

    // Add a new vehicle model
    @PostMapping("/add")
    public ResponseEntity<VehicleModel> createVehicleModel(@RequestBody VehicleModel vehicleModel) {
        VehicleModel savedModel = vehicleModelRepository.save(vehicleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
    }

    // Update an existing vehicle model
    @PutMapping("/{id}")
    public ResponseEntity<VehicleModel> updateVehicleModel(@PathVariable String id, @RequestBody VehicleModel updatedModel) {
        return vehicleModelRepository.findById(id)
                .map(model -> {
                    model.setBrandId(updatedModel.getBrandId());
                    model.setModelName(updatedModel.getModelName());
                    vehicleModelRepository.save(model);
                    return ResponseEntity.ok(model);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a vehicle model
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleModel(@PathVariable String id) {
        if (vehicleModelRepository.existsById(id)) {
            vehicleModelRepository.deleteById(id);
            return ResponseEntity.ok("Vehicle model with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle model not found with ID " + id);
        }
    }
}

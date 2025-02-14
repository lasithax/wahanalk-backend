package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.VehicleBodyType;
import com.agile.wahanalk_backend.model.VehicleBrand;
import com.agile.wahanalk_backend.repository.VehicleBodyTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-body-types")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class VehicleBodyTypeController {

    private final VehicleBodyTypeRepository vehicleBodyTypeRepository;

    public VehicleBodyTypeController(VehicleBodyTypeRepository vehicleBodyTypeRepository) {
        this.vehicleBodyTypeRepository = vehicleBodyTypeRepository;
    }

    // Get all vehicle body types
    @GetMapping("/all")
    public ResponseEntity<List<VehicleBodyType>> getAllVehicleBodyTypes() {
        List<VehicleBodyType> bodyTypes = vehicleBodyTypeRepository.findAll();
        return ResponseEntity.ok(bodyTypes);
    }

    // Get a single vehicle body type by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehicleBodyType> getVehicleBodyTypeById(@PathVariable String id) {
        Optional<VehicleBodyType> bodyType = vehicleBodyTypeRepository.findById(id);
        return bodyType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new vehicle body type
    @PostMapping("/add")
    public ResponseEntity<VehicleBodyType> createVehicleBodyType(@RequestBody VehicleBodyType vehicleBodyType) {
        VehicleBodyType savedBodyType = vehicleBodyTypeRepository.save(vehicleBodyType);
        return ResponseEntity.ok(savedBodyType);
    }

    // Update an existing vehicle body type
    @PutMapping("/{id}")
    public ResponseEntity<VehicleBodyType> updateVehicleBodyType(@PathVariable String id, @RequestBody VehicleBodyType updatedBodyType) {
        return vehicleBodyTypeRepository.findById(id)
        .map(type -> {
                type.setBodyTypeName(updatedBodyType.getBodyTypeName());
                vehicleBodyTypeRepository.save(type);
                return ResponseEntity.ok(type);
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a vehicle body type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleBodyType(@PathVariable String id) {
        if (!vehicleBodyTypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vehicleBodyTypeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

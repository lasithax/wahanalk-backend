package com.agile.wahanalk_backend.controller;

import com.agile.wahanalk_backend.model.VehicleCondition;
import com.agile.wahanalk_backend.repository.VehicleConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-conditions")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class VehicleConditionController {

    @Autowired
    private VehicleConditionRepository vehicleConditionRepository;

    // Get all vehicle conditions
    @GetMapping("/all")
    public ResponseEntity<List<VehicleCondition>> getAllVehicleConditions() {
        List<VehicleCondition> conditions = vehicleConditionRepository.findAll();
        return ResponseEntity.ok(conditions);
    }

    // Get a specific vehicle condition by ID
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehicleCondition> getVehicleConditionById(@PathVariable String id) {
        return vehicleConditionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get a vehicle condition by name
    @GetMapping("/name/{conditionName}")
    public ResponseEntity<VehicleCondition> getVehicleConditionByName(@PathVariable String conditionName) {
        VehicleCondition condition = vehicleConditionRepository.findByConditionName(conditionName);
        return condition != null ? ResponseEntity.ok(condition) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add a new vehicle condition
    @PostMapping("/add")
    public ResponseEntity<VehicleCondition> createVehicleCondition(@RequestBody VehicleCondition vehicleCondition) {
        VehicleCondition savedCondition = vehicleConditionRepository.save(vehicleCondition);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCondition);
    }

    // Update an existing vehicle condition
    @PutMapping("/{id}")
    public ResponseEntity<VehicleCondition> updateVehicleCondition(@PathVariable String id, @RequestBody VehicleCondition updatedCondition) {
        return vehicleConditionRepository.findById(id)
                .map(condition -> {
                    condition.setConditionName(updatedCondition.getConditionName());
                    vehicleConditionRepository.save(condition);
                    return ResponseEntity.ok(condition);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete a vehicle condition
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleCondition(@PathVariable String id) {
        if (vehicleConditionRepository.existsById(id)) {
            vehicleConditionRepository.deleteById(id);
            return ResponseEntity.ok("Vehicle condition with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle condition not found with ID " + id);
        }
    }
}

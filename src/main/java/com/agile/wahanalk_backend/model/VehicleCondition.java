package com.agile.wahanalk_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicleCondition")
public class VehicleCondition {

    @Id
    private String id;
    private String conditionName;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }
}

package com.agile.wahanalk_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicleBodyType")
public class VehicleBodyType {

    @Id
    private String id;
    private String bodyTypeName;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getBodyTypeName() {
        return bodyTypeName;
    }

    public void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName;
    }
}

package com.agile.wahanalk_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicleSubCategory")
public class VehicleSubCategory {

    @Id
    private String id;
    private String subCategoryName;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}

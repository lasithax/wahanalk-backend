package com.agile.wahanalk_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Year;

@Document(collection = "advertisement")
public class Advertisement {

    @Id
    private String id;
    private String userId;
    private String categoryId;
    private String subCategoryId;
    private String conditionId;
    private String brandId;
    private String modelId;
    private String trimEdition;
    private Year yearManufacture;
    private Integer mileage;
    private String fuelTypeId;
    private String transmissionTypeId;
    private String bodyTypeId;
    private String description;
    private Double price;
    private LocalDate advertisementDate;


    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getTrimEdition() {
        return trimEdition;
    }

    public void setTrimEdition(String trimEdition) {
        this.trimEdition = trimEdition;
    }

    public Year getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(Year yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(String fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public String getTransmissionTypeId() {
        return transmissionTypeId;
    }

    public void setTransmissionTypeId(String transmissionTypeId) {
        this.transmissionTypeId = transmissionTypeId;
    }

    public String getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(String bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getAdvertisementDate() {
        return advertisementDate;
    }

    public void setAdvertisementDate(LocalDate advertisementDate) {
        this.advertisementDate = advertisementDate;
    }
}

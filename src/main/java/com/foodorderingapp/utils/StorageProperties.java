package com.foodorderingapp.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "C:\\Users\\User\\Desktop\\IMAGES";

    private String locationFE = "F:\\FOA-final frontend\\FOAFrontend\\app\\assets\\images";

    public String getLocationFE() {
        return locationFE;
    }

    public void setLocationFE(String locationFE) {
        this.locationFE = locationFE;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}


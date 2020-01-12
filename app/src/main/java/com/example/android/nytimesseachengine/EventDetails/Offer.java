package com.example.android.nytimesseachengine.EventDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("inventoryLevel")
    @Expose
    private String inventoryLevel;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("priceCurrency")
    @Expose
    private String priceCurrency;
    @SerializedName("validFrom")
    @Expose
    private String validFrom;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getInventoryLevel() {
        return inventoryLevel;
    }

    public void setInventoryLevel(String inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

}
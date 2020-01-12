package com.example.android.nytimesseachengine.EventDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("addressCountry")
    @Expose
    private String addressCountry;
    @SerializedName("addressLocality")
    @Expose
    private String addressLocality;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressLocality() {
        return addressLocality;
    }

    public void setAddressLocality(String addressLocality) {
        this.addressLocality = addressLocality;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

}
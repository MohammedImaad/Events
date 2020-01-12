package com.example.android.nytimesseachengine.EventDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsAndShiz {

    @SerializedName("@context")
    @Expose
    private String context;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("doortime")
    @Expose
    private String doortime;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("eventStatus")
    @Expose
    private String eventStatus;
    @SerializedName("image")
    @Expose
    private List<String> image = null;
    @SerializedName("location")
    @Expose
    private List<Location> location = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;
    @SerializedName("performers")
    @Expose
    private List<Performer> performers = null;
    @SerializedName("startDate")
    @Expose
    private String startDate;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoortime() {
        return doortime;
    }

    public void setDoortime(String doortime) {
        this.doortime = doortime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}

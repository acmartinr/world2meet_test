package com.world2meet.test.payload.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This payload represent data for Super Hero , should send on request method")
public class SuperHeroRequest {
    @ApiModelProperty(name = "name",required = true,example = "test name")
    private String name;
    @ApiModelProperty(name = "occupation",required = true,example = "occupation name")
    private String occupation;
    @ApiModelProperty(name = "publisher",required = true,example = "publisher name")
    private String publisher;
    @ApiModelProperty(name = "race",required = true,example = "race name")
    private String race;
    @ApiModelProperty(name = "gender",required = true,example = "female")
    private String gender;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

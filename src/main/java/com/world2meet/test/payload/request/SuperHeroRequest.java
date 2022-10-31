package com.world2meet.test.payload.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "This payload represent data for Super Hero , should send on request method")
public class SuperHeroRequest {
    @ApiModelProperty(name = "name",required = true,example = "test name")
    private String name;
    @ApiModelProperty(name = "occupation",required = true,example = "occupation name")
    private String occupation;
    @ApiModelProperty(name = "publisher",required = true,example = "publisher name")
    private String publisher;
    @ApiModelProperty(name = "race",required = true,example = "race number")
    private String race;
    @ApiModelProperty(name = "gender",required = true,example = "female")
    private String gender;
}

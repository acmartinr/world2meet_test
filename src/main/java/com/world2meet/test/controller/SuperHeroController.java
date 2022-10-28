package com.world2meet.test.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Super Hero API")
@RestController
@RequestMapping("/superhero")
public class SuperHeroController {

    @ApiOperation(value = "Return all super heroes budled into response",notes = "Return 204 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "There are not super heroes"),
            @ApiResponse(code = 500,message = "Internal error")
    })
    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
}

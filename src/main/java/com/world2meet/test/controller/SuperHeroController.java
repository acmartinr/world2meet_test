package com.world2meet.test.controller;

import com.world2meet.test.payload.request.SuperHeroRequest;
import com.world2meet.test.payload.response.ErrorResponse;
import com.world2meet.test.persistence.model.SuperHero;
import com.world2meet.test.service.SuperHeroService;
import com.world2meet.test.utils.Constants;
import com.world2meet.test.payload.mapper.SuperHeroRequestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Api(tags = "Super Hero API")
@RestController
@RequestMapping("/superhero")
public class SuperHeroController {

    @Autowired
    SuperHeroService superHeroService;
    @Autowired
    SuperHeroRequestMapper srqm;

    @ApiOperation(value = "Return all super heroes budled into response", notes = "Return 204 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "There are not super heroes"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/")
    public List<SuperHero> getAllSuperHeroes() {
        return superHeroService.getAllSuperHeroes();
    }


    @ApiOperation(value = "Return single super hero with id passed by param into response", notes = "Return 404 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "There are not super hero with data setted"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSuperHeroDetails(@RequestParam Long id) {
        if (superHeroService.getSuperHeroById(id).isPresent()) {
            return new ResponseEntity<>(superHeroService.getSuperHeroById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(Constants.NOT_SUPER_HEROES_FOUND_CODE), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Return super hero list filtered by name", notes = "Return 404 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "There are not super hero with data setted"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/filter")
    public ResponseEntity<Object> getSuperHeroByName(@RequestParam String name) {
        List<SuperHero> superHeroesList = superHeroService.findSuperHeroesByName(name);
        if (!superHeroesList.isEmpty()) {
            return new ResponseEntity<>(superHeroesList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(Constants.NOT_FILTER_SUPER_HEROES_FOUND_CODE), HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "update super hero", notes = "Return 404 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "There are not super hero with id in params"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSuperHero(@PathVariable Long id, @RequestBody SuperHeroRequest superHeroRequest) {
        //Check if super hero exist in data base before update
        if (superHeroService.getSuperHeroById(id).isPresent()) {
            Optional<SuperHero> superHero = superHeroService.updateSuperHeroById(id, srqm.superHeroRequestToSuperHero(superHeroRequest));
            //Check if update was success
            if (superHero.isPresent()) {
                return new ResponseEntity<>(superHero.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ErrorResponse(Constants.UPDATE_ERROR_CODE), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ErrorResponse(Constants.NOT_SUPER_HEROES_FOUND_CODE), HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Remove super hero with id passed by param into request", notes = "Return 404 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "There are not super hero with id in params"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSuperHeroById(@PathVariable("id") Long id) {
        int queryStatus = superHeroService.deleteSuperHeroById(id);
        if (queryStatus == Constants.OBJECT_FOUNDED_CODE) {
            return new ResponseEntity<>(Constants.SUPER_HEROE_WAS_REMOVED_MSG, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(Constants.NOT_SUPER_HEROES_FOUND_CODE), HttpStatus.NOT_FOUND);
        }
    }
}

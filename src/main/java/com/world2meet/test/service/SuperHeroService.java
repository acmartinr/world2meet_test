package com.world2meet.test.service;

import com.world2meet.test.payload.request.SuperHeroRequest;
import com.world2meet.test.persistence.model.SuperHero;
import com.world2meet.test.persistence.repository.SuperHeroRepository;
import com.world2meet.test.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroService {
    @Autowired
    SuperHeroRepository superHeroRepository;

    //getting all super heroes records
    public List<SuperHero> getAllSuperHeroes() {
        return new ArrayList<>(superHeroRepository.findAll());
    }

    //get all super hery by id
    public Optional<SuperHero> getSuperHeroById(Long id) {
        return superHeroRepository.findById(id);
    }

    //update super hero with id in param and all fields to update
    public Optional<SuperHero> updateSuperHeroById(Long id, SuperHero superHeroModel) {
        superHeroModel.setId(id);
        SuperHero superHero = superHeroRepository.save(superHeroModel);
        Optional<SuperHero> userOptional = Optional.of(superHero);
        return userOptional;
    }

    //getting all super heroes records
    public List<SuperHero> findSuperHeroesByName(String name) {
        return new ArrayList<>(superHeroRepository.filterSuperHeroByName(name));
    }

    //getting all prices records
    public int deleteSuperHeroById(Long id) {
        if (getSuperHeroById(id).isPresent()) {
            superHeroRepository.deleteById(id);
            return Constants.OBJECT_FOUNDED_CODE;
        } else {
            return Constants.NOT_SUPER_HEROES_FOUND_CODE;
        }
    }

}
